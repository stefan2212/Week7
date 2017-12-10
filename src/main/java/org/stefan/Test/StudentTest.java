package org.stefan.Test;

import org.junit.Test;
import org.stefan.dao.StudentDAO;
import org.stefan.dao.StudentDAOImpl;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.services.StudentService;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class StudentTest {
    private static StudentService studentService;
    private static StudentDAO studentDAO;

    @org.junit.Before
    public void setUp() throws Exception {
         studentService = StudentService.getInstance();
         studentDAO = new StudentDAOImpl();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getStudents() throws Exception {
        List<Student> listOfStudents = studentService.getStudents();
        assertTrue(listOfStudents.size() != 0);
    }

    @org.junit.Test
    public void getUnAllocatedStudents() throws Exception {
        List<Student> unAllocatedStudents =  studentService.getUnAllocatedStudents();
        assertTrue(unAllocatedStudents.size() == 1);
    }

    @org.junit.Test
    public void getProjectWithStudentPreferences() throws Exception {
        List<StudentsProject> listOfPairs= studentService.getProjectWithStudentPreferences();
        assertTrue(listOfPairs.size() == 1);
    }


    @Test
    public void studentInsert()
    {
        List<Student> listOfStudents = studentService.getStudents();
        Student newStudent = new Student();
        newStudent.setName("Dummy Student");
        newStudent.setEmail("no");
        studentService.addNewStudent(newStudent);
        List<Student> updatedListOfStudents = studentService.getStudents();
        assertTrue(updatedListOfStudents.size()== listOfStudents.size()+1);
    }

    @Test
    public void studentDelete()
    {
        List<Student> listOfStudent = studentService.getStudents();
        studentService.deleteStudentById(32);
        List<Student>updatedStudentsList = studentService.getStudents();
        assertTrue(listOfStudent.size() == updatedStudentsList.size() + 1);
    }

    @Test
    public void studentUpdate()
    {
        String newName = "JOHN DOE";
        studentService.updateStudentById(32,newName);
        Student updatedStudent =studentDAO.getStudentById(32);
        assertTrue(updatedStudent.getName().equals(newName));
    }
}
