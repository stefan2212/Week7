package org.stefan;

import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.services.StudentService;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        StudentService studentService = StudentService.getInstance();
        List<Student> students = studentService.getStudents();
        System.out.println("All the students" + students);
        students = studentService.getUnAllocatedStudents();
        System.out.println("Students that have not applied to all projects" + students);
        List<StudentsProject> projects = studentService.getProjectWithStudentPreferences();
        System.out.println("Student and level of preferences" + projects);

    }

}
