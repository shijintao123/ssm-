function formateState(val){
    if(val==0){
        return "暂缓流逝";
    }
    if(val==1){
        return "确定流逝";
    }
}
function formateOp(val, row) {
    var state=row.state;
    if(state==0){
        var  href="javascript:openAddReprieveDataTab("+row.id+")";
        return "<a href="+href+">添加暂缓<a>";
    }else{
        return "确认流失";
    }
}
function openAddReprieveDataTab(lossId) {
    window.parent.openTab('客户流失_'+lossId,ctx+"/customerReprieve/index?id="+lossId);
}
function queryCustomerLossByParams() {
    $('#dg').datagrid('load',{
        cusNo: $('#cusNo').val(),
        cusName: $('#cusName').val(),
        createDate: $('#time').datebox('getValue')
    })
}