package org.stefan.servlets;

import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = {"/students"})
public class StudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        this.studentService = StudentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        List<Student> students = studentService.getStudents();
        printWriter.write("All the students" + students + '\n');
        students = studentService.getUnAllocatedStudents();
        printWriter.write("Students that have not applied to all projects" + students +'\n');
        List<StudentsProject> projects = studentService.getProjectWithStudentPreferences();
        printWriter.write("Student and level of preferences" + projects +'\n');
    }
}
