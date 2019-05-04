function formateGrade(val) {
    if(val==0){
        return "根级节点";
    }
    if(val==1){
        return "一级节点";
    }
    if(val==2){
        return "二级节点";
    }
}

function  queryModulesByParams() {
    $("#dg").datagrid("load",{
       moduleName:$("#moduleName").val(),
       parentId:$("#pid").val(),
        grade:$("#grade").combobox("getValue"),
        optValue:$("#optValue").val()
    });
}

function openAddModuleDailog (){
    openAddOrUpdateDlg("dlg","添加模块");
}
$(function(){
    $("#parentMenu").hide();
   $("#grade02").combobox({
       onChange:function (val) {
           console.log(val);
           if(val==0){
               $("#parentMenu").hide();
           }else{
               $("#parentMenu").show();
               //
               $('#parentId02').combobox({
                   url:ctx+'/module/queryByModuleGrade?grade='+(val-1),
                   valueField:'id',
                   textField:'moduleName'
               });

           }
       }
   })
});
function saveOrUpdateModule () {
    saveOrUpdateData("fm",ctx+"/module/saveOrUpdateModule ","dlg",queryModulesByParams);
}
function deleteModule (){
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请至少选中一条记录进行删除!");
        return;
    }
    $.messager.confirm("来自crm","确定删除选中的条记录?",function (r) {
        if(r){
            $.ajax({
                type:"post",
                url:ctx+"/module/deleteModule?optValue="+rows[0].optValue,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        // 刷新datagrid
                        $.messager.alert("来自crm",data.msg,"info");
                        queryModulesByParams();
                    }else{
                        $.messager.alert("来自crm",data.msg,"error");
                    }
                }
            })
        }
    })
}