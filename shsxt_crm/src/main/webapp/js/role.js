function queryRolesByParams (){
    $("#dg").datagrid("load",{
        roleName:$("#roleName").val(),
        createDate:$("#time").datebox("getValue")
    })
}
function openAddRoleDailog(){
    openAddOrUpdateDlg('dlg',"添加角色")
}
 function openModifyRoleDialog (){
     openModifyDialog("dg","fm","dlg","修改角色")
}
function saveOrUpdateRole (){
     saveOrUpdateData("fm",ctx+"/role/saveOrUpdateRole","dlg",queryRolesByParams)
}
function deleteRole(){
    deleteData("dg",ctx+"/role/deleteBatch",queryRolesByParams);
}
function openRelationPermissionDialog (){
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择一条数据进行授权!");
        return ;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","只能选择一条数据进行授权!");
        return ;
    }
    $("#permissionDlg").dialog("open");
    $("#roleId").val(rows[0].id),
    loadTree(rows[0].id);
}
var treeObj;
function  loadTree(roleId){
    //加载树
    var setting = {
        check: {
            enable: true,
            chkboxType : { "Y" : "ps", "N" : "ps" },
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: zTreeOnCheck
        }
    };
    $.ajax({
        url:ctx+"/role/queryTree?roleId="+roleId,
        success:function (data) {
            var zNodes =data;
            treeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
            zTreeOnCheck();
        }
    })
function zTreeOnCheck(){
    var nodes = treeObj.getCheckedNodes(true);
    var moduleIds="";
    for(var i=0;i<nodes.length;i++){
        moduleIds+="moduleIds="+nodes[i].id+"&";
    }
    $("#moduleIds").val(moduleIds);
    }
}
function doGrant (){
    var roleId=$("#roleId").val();
    var moduleIds=$("#moduleIds").val();

    $.ajax({
       url:ctx+"/role/doGrand?roleId="+roleId+"&"+moduleIds,
        success:function (data) {
            if(data.code==200){
                //关闭弹窗
                $("#permissionDlg").dialog("close");
            }else{
                $.messager.alert("来自Crm",data.msg,"error");
            }
        }
    });
}