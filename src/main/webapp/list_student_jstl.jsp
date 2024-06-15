<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@page import="com.Demo.web.jdbc.util.Student"%>
<%@page import="java.util.List"%>
<html>
<body>
<head>
<title>Student Tracker APP</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<div id="wrapper">
	<div id="header">
		<h2>FooBar University</h2>
	</div>
</div>

<div id="container">
	<br /> <input type="button" value="Add Student"
		onclick="window.location.href='add-student.jsp'; "
		class="add-student-button" />
	<hr>
	<div id="content">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
			<c:forEach var="temStud" items="${getStudent}">
				<c:url var="tempLinkUpdate" value="StudentControllerServlet">
					<c:param name="command" value="Load" />
					<c:param name="getId" value="${temStud.id}" />
				</c:url>
				<c:url var="tempLinkDelete" value="StudentControllerServlet">
					<c:param name="command" value="delete" />
					<c:param name="getId" value="${temStud.id}" />
				</c:url>
				<tr>
					<td>${temStud.id}</td>
					<td>${temStud.firstName}</td>
					<td>${temStud.lastName}</td>
					<td>${temStud.email}</td>
					<td><a href="${tempLinkUpdate}">update</a>|<a href="${tempLinkDelete}"
					onclick="if(!(confirm('Are you Sure you want to delete ? '))) return false">delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</div>

</body>


</html>