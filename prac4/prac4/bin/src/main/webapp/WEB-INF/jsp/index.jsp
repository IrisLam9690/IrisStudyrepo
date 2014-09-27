<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/authority.js"></script>

<title>Welcome to MyREST</title>
</head>
<body>
	<p>
		Welcome to MyREST!
	</p>
	<p>
		<form id="logonform" method="post">
			username:<input name="username" type="text"><br>
			password:<input name="password" type="password"><br>
			<input type="hidden" name="source" value="pc">
			<button type="button" onclick="javascript:dologon()">log on</button>
		</form>
	</p>
	<p id="logonresult"></p>
	<p>
		<a href="/MyREST_Maven/register">register</a>
	</p>
</body>
</html>