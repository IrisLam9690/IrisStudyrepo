<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/MyREST_Maven_Statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/home.js"></script>
<script type="text/javascript" src="/MyREST_Maven_Statics/js/upload.js"></script>

<title>Home</title>
</head>
<body>
<div id="whole">
	<p>
	<c:set var="user" value="${homepc.userBean}"></c:set>
	欢迎! 来自 ${user.schoolBean.name} 的 ${user.username}！
	</p>	
	<p><a href="/MyREST_Maven/logout">注销</a></p>
	<p id="items">
	<c:forEach var="item" items="${homepc.items.list}" >
		${item.title}<br/>
	</c:forEach>
	</p>
	<p id="readmore"><a href="javascript:void(0)">查看更多</a></p>	
	<p><a href="/MyREST_Maven/user/${user.id}/step1">填写信息</a></p>	
	<p>
    Select a file : <input type="file" name="uploadedFile" id="uploadedFile" size="50" /><br />  
    <input type="button" value="Upload It" onclick="javascript:uploadfile()" />
	</p>
	
    <p id="uploadresult"></p>
    <p>
    <a href="/MyREST_Maven/upload/${user.id}">查看我的文件</a>
    </p>
</div>	
</body>
</html>