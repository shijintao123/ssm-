function queryUsersByParams (){
    $("#dg").datagrid("load",{
        userName:$("#userName").val(),
        email:$("#email").val(),
        phone:$("#phone").val(),
    });
}
$(function(){
   $("#dlg").dialog({
       "onClose":function () {
           $("#fm").form("clear");
       }
   })
});
function openAddUserDailog (){
    openAddOrUpdateDlg("dlg","添加用户");
}
function saveOrUpdateUser () {
    saveOrUpdateData("fm",ctx+"/user/saveOrUpdateUser","dlg",queryUsersByParams);
}
function openModifyUserDialog (){
    openModifyDialog("dg","fm","dlg","修改用户");
}
function deleteUser(){
    deleteData("dg",ctx+"/user/deleteUser",queryUsersByParams);
}