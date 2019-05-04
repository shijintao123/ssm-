//加载数据表格
    var sid=$("#saleChanceId").val();
    var devResult=$("#devResult").val();
    $("#dg").edatagrid({
        url: ctx+'/cusDevPlan/queryCusDevPlanByParams?sid='+sid,
        saveUrl: ctx+"/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId="+sid,
        updateUrl: ctx+"/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId="+sid,
    });
$(function(){
   if(devResult==2||devResult==3){
       //隐藏
       $("#toolbar").hide();
       //不可编辑
       $("#dg").edatagrid("disableEditing");
   }
});
function addRow(){
    $("#dg").edatagrid("addRow");
}
function saveOrUpdateCusDevPlan (){
    $("#dg").edatagrid("saveRow");
}
//删除计划
function delCusDevPlan (){
    deleteData("dg",ctx+"/cusDevPlan/deleteBatch",function () {
       $("#dg").edatagrid("load");
    });
}
//开发成功
function updateSaleChanceDevResult (devResult){
    $.ajax({
       url:ctx+"/saleChance/updateSaleChance",
        type:"post",
        data:{
            id:sid,
            devResult:devResult
        },
        success:function (result) {
           if(result.code==200){
                //隐藏
               $("#toolbar").hide();
               //不可编辑
               $("#dg").edatagrid("disableEditing");
           }else{
               $.messager.alert("来自crm","只能选择一行进行更新","error");
           }
        }
    });
}