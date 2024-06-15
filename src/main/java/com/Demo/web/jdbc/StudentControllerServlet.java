package com.Demo.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.Demo.web.jdbc.util.Student;
import com.Demo.web.jdbc.util.StudentDataDBUtil;

/**
 * Servlet implementation class StudentControllerServlet
 */

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDataDBUtil studentUtil;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;



	@Override
	public void init() throws ServletException {
		try {
			studentUtil = new StudentDataDBUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException();
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String theCommand = request.getParameter("command");
		if(theCommand == null) {
			ListofStudents(request,response);
		} else if(theCommand.equalsIgnoreCase("add")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");

			Student student = new Student(firstName, lastName, email);
			try {
				studentUtil.addStudent(student);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListofStudents(request, response);
		} else if(theCommand.equalsIgnoreCase("load")) {
			Optional<Student>  oStudent  = getStudentlist().stream().filter(i ->i.getId() == Integer.valueOf(request.getParameter("getId")))
					.map(Stud -> {
						Student s = new Student(Stud.getId(), Stud.getFirstName(), Stud.getLastName(), Stud.getEmail());
						return s;
					}).findFirst();
			request.setAttribute("getStudentDetails", oStudent.get());
			RequestDispatcher dispatcher = request.getRequestDispatcher("update-student.jsp");
			dispatcher.forward(request, response);
		} else if(theCommand.equalsIgnoreCase("update")) {

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String id = request.getParameter("id");
			Student student = new Student(Integer.valueOf(id),firstName, lastName, email);
			try {
				studentUtil.updateStudent(student);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListofStudents(request, response);
		
		} else if(theCommand.equalsIgnoreCase("delete")) {
			try {
				studentUtil.deleteStudent(request.getParameter("getId"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListofStudents(request, response);
		}


	}

	void ListofStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setAttribute("getStudent", getStudentlist());
		RequestDispatcher dispatcher = request.getRequestDispatcher("list_student_jstl.jsp");
		dispatcher.forward(request, response);

	}

	List<Student> getStudentlist() {
		List<Student> list = null;
		try {
			list = studentUtil.getStudentList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
