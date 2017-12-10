package org.stefan.dao;

import org.stefan.entities.Project;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StudentDAO {

    List<Student> getStudents();

    List<Student> getUnallocatedStudent();

    List<StudentsProject> getProjectWithStudentPreferences();

    void addStudent(Student newStudent);

    void deleteStudentById(long id);

    void updateStudentById(long id,String newName);

    Student getStudentById(long id);
}
