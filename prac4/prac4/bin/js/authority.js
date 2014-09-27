/**
 * @author Niuzhixiang
 */
function doregister(){
	//Using $.post()
  	/*$.post("/MyREST/register", $("#registerform").serialize(), function(){
  		alert("aaa");
  	});*/
  	//Using $.ajax()
	$.ajax({
  		async:true,
  		type:"POST",
  		dataType:"json",
  		//防止重复提交 
  		mode:"block",
  		url:"/MyREST_Maven/register",
  		data:$("#registerform").serialize(),
  		complete:function(xhr){
  			if (xhr.status==200) {
  				$("#registerform").hide();
  				$("#registerresult").html("register success!<br/><a href=\"/MyREST_Maven\">log on now</a>");
  			} else if (xhr.status==400) {
  				$("#registerresult").html("user exists!");
  			} else {
  				$("#registerresult").html("register error!");
  			}			
  		}
  		/*success:function(data){
  			if(data.registerResultBean.status=="register_success"){
  				$("#registerform").hide();
  				$("#registerresult").html("register success!<br/><a href=\"/MyREST\">log on now</a>");
  			} else if (data.registerResultBean.status=="user_exists"){
  				$("#registerresult").html("user exists!");
  			} else {
  				//window.location("");
  				$("#registerresult").html("register error!");
  			}
  		}*/		
  	});
  
}



/**
 * @author Niuzhixiang
 */
function dologon(){
	var xmlhttprequest =
	$.ajax({
		async:true,
		type:"POST",
		dataType:"json",
		mode:"block",
		url:"/MyREST_Maven/logon",
		data:$("#logonform").serialize(),
		complete:function(xhr){
			if (xhr.status==200) {
				window.location.href = xhr.getResponseHeader('Location');
			} else if (xhr.status==400) {
				$("#logonresult").html("wrong username or password!");
			} else {
				$("#logonresult").html("server error!");
			}
		}
		/*success:function(data, textStatus){
			if(data.logonResultBean.status=="logon_fail"){
				$("#logonresult").html("wrong username or password!");
				alert(textStatus);
			}
			if(data.logonResultBean.status=="server_error"){
				$("#logonresult").html("server error!");
			}
			if(data.logonResultBean.status=="logon_success"){
				$("#logonresult").html("logon success!");
				alert(textStatus);
			}
		}*/		
	});
}
