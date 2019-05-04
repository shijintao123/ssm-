function formatterDevResult(value){
    if(value=="0"){
        return "未开发";
    }
    if(value==1){
        return "开发中";
    }
    if(value==2){
        return "开发成功";
    }
    if(value==3){
        return "开发失败";
    }
}
function  formatterOp(value,row) {
    if(row.devResult==2||row.devResult==3){
        var href="javascript:openSaleChanceInfoDialog("+'"详情机会数据"'+","+row.id+")";
        return "<a href='"+href+"'>查看详情</a>";
    }
    if(row.devResult==0||row.devResult==1){
        var href="javascript:openSaleChanceInfoDialog("+'"开发机会数据"'+","+row.id+")";
        return "<a href='"+href+"'>开发</a>";
    }
}
function querySaleChancesByParams() {
    $('#dg').datagrid('load',{
        customerName:$("#customerName").val(),
        devResult:$("#devResult").combobox("getValue"),
        createDate:$("#time").datebox("getValue")
    });
}
function openSaleChanceInfoDialog(title,id){
    window.parent.openTab(title+"_"+id,ctx+"/cusDevPlan/index?sid="+id);
}