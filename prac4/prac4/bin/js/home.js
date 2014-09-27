/**
 * @author Niuzhixiang
 */
$(document).ready(function(){
	
	if (typeof start == "undefined") {
		start = 0;	
	};
	if (typeof size == "undefined") {
		size = 5;	
	};
	
	$("#readmore").click(function() {
		start+=size;
		$.ajax({
		async:true,
		type:"GET",
		dataType:"json",
		url:window.location.href + "?start=" + start + "&size=" + size,
		beforeSend:function(){
			$("#readmore").html('加载中...');
		},
		success:function(data){
			var items = data.listContainer.list;
			if(typeof items=="undefined"){
				$("#items").append('</br>');
				$("#items").append('没有更多了');
				$("#readmore").hide();
			}		
			else {
				if(items.length > 1){
					for(var key in items){
						$("#items").append(items[key].title);
						$("#items").append('</br>');
					}
				}
				else {
					$("#items").append(items.title);
					$("#items").append('</br>');
				}
			}	
		},
		complete:function(){
			$("#readmore").html('<a href="javascript:void(0)">查看更多</a>');
		}	
		});
	});
	
});

