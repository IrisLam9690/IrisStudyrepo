<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/authority.js"></script>
<title>Register</title>
</head>
<body>

<form id="registerform" method="post">
	username:<br/>
	<input type="text" id="username" name="username"><br>
	email:<br/>
	<input type="text" id="email" name="email"><br>
	password:<br/>
	<input type="password" id="password" name="password"><br>
	school:<br/>
	<select name="schoolid">
		<c:forEach var="school" items="${register.schools.list}">
			<option value="${school.id}">${school.name}</option>
		</c:forEach>
	</select>
	<br/>
	<button type="button" onclick="javascript:doregister()">register</button>
</form>
<p id="registerresult"></p>
</body>
</html>