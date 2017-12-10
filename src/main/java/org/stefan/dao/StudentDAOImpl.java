package org.stefan.dao;

import org.stefan.entities.Project;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext(name = "Week7")
    private EntityManager entityManager;

    public StudentDAOImpl() {
        this.entityManager = PersistenceUtil.getEntityManager();
    }

    @Override
    public List<Student> getStudents() {

        Query query = entityManager.createQuery("Select s from Student as s");
        List<Student> students = query.getResultList();
        return students;
    }

    @Override public List<Student> getUnallocatedStudent() {
        Query query = entityManager.createQuery("select p from Project as p");
        int numberOfProjects = query.getResultList().size();
        Query query2 = entityManager.createQuery("Select distinct s.name from Student as s, Project as p where s.studentsProject.size < ?");
        List<Student> students = query2.setParameter(0,numberOfProjects).getResultList();
        return students;
    }

    @Override public List<StudentsProject> getProjectWithStudentPreferences() {
        Query query = entityManager.createQuery("Select p from StudentsProject as p ");
        List<StudentsProject> projects = query.getResultList();
        return projects;
    }

    @Override
    public void addStudent(Student newStudent) {
        entityManager.getTransaction().begin();
        entityManager.persist(newStudent);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteStudentById(long id) {
        entityManager.getTransaction().begin();
        Student toDeleteStudent = entityManager.find(Student.class,id);
        entityManager.remove(toDeleteStudent);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateStudentById(long id,String newName) {
        entityManager.getTransaction().begin();
        Student toUpdateStudent = entityManager.find(Student.class,id);
        toUpdateStudent.setName(newName);
        entityManager.getTransaction().commit();
    }

    @Override
    public Student getStudentById(long id) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class,id);
        entityManager.getTransaction().commit();
        return student;
    }
}
