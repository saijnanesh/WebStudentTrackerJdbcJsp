
<!DOCTYPE html>
<%@page import="com.Demo.web.jdbc.util.StudentDataUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.Demo.web.jdbc.util.Student"%>
<%@page import="java.util.List"%>
<html>
<body>
<head>
<title>Student Tracker APP</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
List<Student> list = StudentDataUtil.getStudentRequest();
%>

<div id="wrapper">
	<div id="header">
		<h2>FooBar University</h2>
	</div>
</div>

<div id="container">
	<br /> <input type="button" value="Add Student"
		onclick="window.location.href = 'add-student.jsp'; return=false"
		class="add-student-button" />
	<hr>
	<div id="content">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
			</tr>

			<%
			for (Student std : list) {
			%>
			<tr>
				<td><%=std.getId()%></td>
				<td><%=std.getFirstName()%></td>
				<td><%=std.getLastName()%></td>
				<td><%=std.getEmail()%></td>
			</tr>
			<%
			}
			%>

		</table>
	</div>
</div>

<%=list%>

</body>


</html>