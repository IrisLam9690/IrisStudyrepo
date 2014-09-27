<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/step.js"></script>
<title>Step1</title>
</head>
<body>
	<form method="post">
		data1：<input type="text" name="data1" id="data1" /><br>
		data2: <input type="text" name="data2" id="data2" /><br>
		<input type="button" onclick="javascript:nextStep()" value="下一步" />
	</form>
</body>
</html>