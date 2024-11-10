/**
 * Created by bdqn on 2016/5/3.
 */
function register() {
    //获取相关字段的值
    var loginName = $("input[name='loginName']").val();
    var userName = $("input[name='userName']").val();
    var password = $("input[name='password']").val();
    var confirmPassword = $("input[name='confirmPassword']").val();
    var email = $("input[name='email']").val();
    var mobile = $("input[name='mobile']").val();
    var identityCode = $("input[name='identityCode']").val();
    var address = $("input[name='address']").val();
    var sex = $("input[name='sex']:checked").val();
    //判断密码是否一致
    if(loginName==null || loginName==""){
        showMessage("用户名不能为空.");
        return;
    }

    if(loginName.length<2 || loginName>10){
        showMessage("登录名不能小于两个字符或者大于十个字符.");
        return;
    }

    if(userName==null || userName==""){
        showMessage("真实姓名不能为空.");
        return;
    }

    if(userName.length<2 || userName>10){
        showMessage("真实姓名不能小于两个字符或者大于十个字符.");
        return;
    }

    if (password != confirmPassword) {
        showMessage("两次输入的密码不一致.");
        return;
    }
    //判断密码是否为空
    if (password =="") {
        showMessage("密码不能为空");
        return;
    }
    //验证邮箱格式
    if(email!=null && email!="" && !checkMail(email)){
    	showMessage("邮箱格式不正确");
        return;
    }
    //验证邮箱格式
    if(mobile!=null && mobile!="" && !checkMobile(mobile)){
    	showMessage("手机格式不正确");
        return;
    }
     //验证邮箱格式
    if(identityCode!=null && identityCode!="" && !checkIdentityCode(identityCode)){
    	showMessage("身份证号格式不正确");
        return;
    }
    
    $.ajax({
        url: contextPath + "/RegisterServlet",
        method: "post",
        data: {
            action: "login",
            loginName: loginName,
            userName: userName,
            password: password,
            sex: sex,
            email: email,
            mobile: mobile,
           action: "doRegister",
            identityCode: identityCode,
            address: address
        },
        success: function (jsonStr) {
            var result = eval("("+jsonStr+")");
            if (jsonStr.status == 1) {
                showMessage(result.message);
                window.location.href = contextPath + "/LoginServlet?action=toLogin";
            } else {
                showMessage(result.message);
            }
        }
    });
}


function checkMail(mail) {
  var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  if (filter.test(mail)) 
	  return true;
  else {
	 return false;}
}

function checkMobile(phone) {
  var filter  = /^\d{5,11}$/;
  if (filter.test(phone)) 
	  return true;
  else {
	 return false;
  }
}

function checkIdentityCode(identityCode) {
	  var filter  = /^\w{18}$/;
	  if (filter.test(identityCode)) 
		  return true;
	  else {
		 return false;
	  }
}
