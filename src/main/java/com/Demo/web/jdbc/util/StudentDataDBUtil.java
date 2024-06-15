package com.Demo.web.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class StudentDataDBUtil {

	private DataSource dataSource;

	public StudentDataDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Student> getStudentList() throws Exception {
		List<Student> studentList = new ArrayList<Student>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rSet = null;

		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rSet = stmt.executeQuery("select * from student");
			while (rSet.next()) {
				studentList.add(new Student(rSet.getInt("id"), rSet.getString("first_name"), rSet.getString("last_name"), rSet.getString("email")));
			}
		} finally {
			close(con,stmt,rSet);
		}
		return studentList;
	}

	public void addStudent(Student student) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rSet = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement("insert into Student (first_name,last_name,email) values (?,?,?)");
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());
			stmt.execute();
		} finally {
			close(con,stmt,rSet);
		}
	}
	
	public void updateStudent(Student student) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rSet = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement("update Student set first_name= ? , last_name = ?, email = ? where id = ?");
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());
			stmt.setInt(4, student.getId());
			stmt.execute();
		} finally {
			close(con,stmt,rSet);
		}
	}
	
	private void close(Connection con, Statement stmt, ResultSet rSet) {
		try {
			if(!con.isClosed()) {
				con.close();
			}
			if(!rSet.isClosed()) {
				rSet.close();
			}
			if(!stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
		}
	}

	public void deleteStudent(String id) throws NumberFormatException, SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rSet = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement("delete from student where id = ? ");
			
			stmt.setInt(1, Integer.valueOf(id));
			stmt.execute();
		} finally {
			close(con,stmt,rSet);
		}
	}


}
