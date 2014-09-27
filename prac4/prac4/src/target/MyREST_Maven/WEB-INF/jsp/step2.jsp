<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/step.js"></script>
<title>Step2</title>
</head>
<body>
	<form method="post" id="wholeform">
		<input type="hidden" name="data1" id="data1" />
		<input type="hidden" name="data2" id="data2" />
		data3：<input type="text" name="data3" id="data3" /><br>
		data4: <input type="text" name="data4" id="data4" /><br>
		<input type="button" onclick="javascript:stepComplete()" value="完成" />
	</form>
</body>
</html>