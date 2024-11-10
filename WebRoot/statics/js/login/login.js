function login(){
	//获取登入账号
	var loginName=$("#loginName").val();
	//获取登入密码
	var password=$("#password").val();
	$.ajax({
		//请求的路径
		url:contextPath+"/LoginServlet",
		//请求方式 
		method:"post",
		//请求发送过去的数据  和需要调用的后台方法名
		data:{loginName:loginName,password:password,action:"login"},
		//发送请求的格式
		dataType:"json",
		//返的数据进行处理
		success:function(jsonStr){
			if(jsonStr.status==1){
				window.location.href=contextPath+"/HomeServlet?action=index";
			}else{
				showMessage(jsonStr.message);
			}
		}
	})
}