package com.Demo.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class JdbcServlet
 */
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//step 1: set up print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		//Step 2: connection to DB
		Connection myCon = null;
		Statement stmt = null;
		ResultSet rSet = null;
		
		try {
			myCon = dataSource.getConnection();
		//Step 3: create sql statements
			String sql = "select * from student";
			stmt = myCon.createStatement();
		//step 4: execute sql qry
			rSet = stmt.executeQuery(sql);
		//stpe 5 prcoess the result set
			while (rSet.next()) {
				String email = rSet.getString("email");
				out.println(email);
			}
		} catch(Exception e) {
			
		}
		
	}
}
