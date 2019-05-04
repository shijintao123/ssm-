function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}
function logout(){
    $.messager.confirm("来自crm","您确定要退出吗？",function (r) {
        if(r){
            $.removeCookie("userIdStr");
            window.location.href=ctx+"/index";
        }
    })
}
function openPasswordModifyDialog(){
    $("#dlg").dialog("open");
}
function modifyPassword(){
    $("#fm").form('submit', {
        url: ctx+"/user/updatePassword",
        onSubmit: function(){
            //做表单校验
        return $(this).form('validate');
    },
    success: function(result){
        result=JSON.parse(result);
        if(result.code==200){
            $.messager.alert("来自crm",result.msg,"info",function () {
                    $.removeCookie("userIdStr");
                    window.location.href=ctx+"/index";
            })
        }else{
            $.messager.alert("来自crm",result.msg,"error");
        }
        console.log(result);
    }
});
}
function closePasswordModifyDialog(){
    $("#dlg").dialog("close");
}