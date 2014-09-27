/**
 * @author Niuzhixiang
 */
function nextStep(){
	
	var url = window.location.href.toString();
	var urlpart = url.split("/");
	var userid = urlpart[5];
	
	/*原生的创建cookie的方式
	var data1 = document.getElementById("data1").value;
	var data2 = document.getElementById("data2").value;
	document.cookie="data1="+data1;
	document.cookie="data2="+data2;*/
	
	$.cookie('data1',document.getElementById("data1").value);
	$.cookie('data2',document.getElementById("data2").value);
	
	window.location.href="/MyREST_Maven/user/" + userid + "/step2";
}

function stepComplete(){
	
	var url = window.location.href.toString();
	var urlpart = url.split("/");
	var userid = urlpart[5];
	
	$.cookie('data3',document.getElementById("data3").value);
	$.cookie('data4',document.getElementById("data4").value);
	
	/*
	手动创建json数据
	var formdata = {
		data1 : $.cookie('data1'),
		data2 : $.cookie('data2'),
		data3 : $.cookie('data3'),
		data4 : $.cookie('data4')
	}*/
	
	$.ajax({
			async : true,  
            type : "POST",
            url : "/MyREST_Maven/user/" + userid + "/stepcomplete",
            mode : "block",
            dataType : "json",
            success : function(data){
                alert("sucess");
            },
            complete : function(xhr){
            	if (xhr.status==200) {
            		alert("success");
            	};
            	//删除cookie
            	$.removeCookie('data1');
            	$.removeCookie('data2');
            	$.removeCookie('data3');
            	$.removeCookie('data4');
            }          
        });
}
