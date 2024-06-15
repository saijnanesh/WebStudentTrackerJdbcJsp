<!DOCTYPE html>

<%@page import="com.Demo.web.jdbc.util.Student"%>
<html>
<head>
<title>Add Student Form</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-button.css">
</head>
<body>

<%
Student student =(Student) request.getAttribute("getStudentDetails");
%>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Add Student</h3>
		<form action="StudentControllerServlet" method="get">
			<input type="hidden" name="command" value="update">
			<input type="hidden" name="id" value="<%= student.getId() %>" />
			<table>
				<tbody>
					<tr>
						<td><label>First Name: </label></td>
						<td><input type="text" name="firstName" value="<%= student.getFirstName()==null ? "" : student.getFirstName() %>"/></td>
					</tr>
					<tr>
						<td><label>Last Name: </label></td>
						<td><input type="text" name="lastName" value="<%= student.getLastName() %>"/></td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td><input type="text" name="email" value="<%= student.getEmail() %>"/></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		<a href="StudentControllerServlet"> backToList</a>
	</div>
</body>
</html>