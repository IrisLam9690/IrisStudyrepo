/**t
 * @author Niuzhixiang
 */

function getuserid(){
	var url = window.location.href.toString();
	var urlpart = url.split("/");
	return urlpart[5];
}

function uploadfile(){
	var options = {		
		url:"/MyREST_Maven/upload/"+getuserid(),
		secureuri:false,
		fileElementId:"uploadedFile",
		dataType:"text",
		beforeSend:function(){
			$("#uploadresult").html("uploading...");
  		},
		success:function(data, status){
			$("#uploadresult").html("upload success!!!");
		},
		error:function(data, status, e){
			console.log(e);
			$("#uploadresult").html("upload fail!!!");
		}
	};
	$.ajaxFileUpload(options);
}
