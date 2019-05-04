$(function () {
    var lossId=$("#lossId").val();
    var state=$("#state").val();
    $('#dg').edatagrid({
            url: ctx+'/customerReprieve/queryCusReprieveByParams?lossId='+lossId,
            saveUrl: ctx+'/customerReprieve/saveOrUpdateRep?lossId='+lossId,
            updateUrl: ctx+'/customerReprieve/saveOrUpdateRep?lossId='+lossId
    });
    if(state==1){
        $("#toolbar").hide();
        $("#dg").edatagrid("disableEditing");
    }
});
function addRow () {
    $("#dg").edatagrid("addRow");
}
function saveOrUpdateCusDevPlan () {
    $("#dg").edatagrid("saveRow");

}
//确认流失
function confirmLoss () {
    var lossId=$("#lossId").val();
    $.ajax({
        url:ctx+"/customerReprieve/updateState",
        type:"post",
        data:{
            lossId:lossId
        },
        success:function (result) {
            if(result.code==200){
                //隐藏
                $("#toolbar").hide();
                //不可编辑
                $("#dg").edatagrid("disableEditing");
            }else{
                $.messager.alert("来自crm",result.msg,"error");
            }
        }
    });
}
//删除计划
function delCusDevPlan (){
    deleteData("dg",ctx+"/customerReprieve/deleteBatch",function () {
        $("#dg").edatagrid("load");
    });
}