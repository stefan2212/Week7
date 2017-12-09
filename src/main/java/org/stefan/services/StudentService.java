package org.stefan.services;

import org.stefan.dao.StudentDAO;
import org.stefan.dao.StudentDAOImpl;
import org.stefan.entities.Project;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;

import javax.ejb.EJB;
import java.util.List;


public class StudentService {

    private static StudentService studentService;

    @EJB
    private StudentDAO studentDAO;

    private StudentService() {
        this.studentDAO = new StudentDAOImpl();
    }

    public synchronized static StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public List<Student> getStudents() {
        return studentDAO.getStudents();
    }

    public List<Student> getUnAllocatedStudents(){
        return studentDAO.getUnallocatedStudent();
    }

    public List<StudentsProject> getProjectWithStudentPreferences() {
        return studentDAO.getProjectWithStudentPreferences();
    }

}
