function login(){
    var username=$("#username").val();
    var password=$("#password").val();
    if(isEmpty(username)){
        alert("用户名为空！")
        return;
    }
    if(isEmpty(password)){
        alert("密码为空！")
        return;
    }
    $.ajax({
        url:ctx+"/user/login",
        type:"post",
        dataType:"json",
        data:{
            userName:username,
            password:password
        },
        success:function(result){
            if(result.code==200){
                alert(result.msg);
                //存入cookie
                $.cookie("userIdStr",result.result.userIdStr);
                window.location.href=ctx+"/main";
            }else{
                alert(result.msg);
            }
        }
    })
}