function saveCustomerServe () {
    $("#fm").form("submit",{
        url:ctx+"/customerServe/saveOrUpdateServe",
        onSubmit:function(){
            return $(this).form("validate");
        },
        success:function (data) {
            /**
             * data 为原始的json 字符串
             *   需要转换为js 对象
             */
            data=JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自crm",data.msg,"info");
                //清空表单
                $("#fm").form("clear");
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    })
}
function queryCustomerServesByParams () {
    $("#dg").datagrid('load',{
        customer:$("#cusName").val(),
        myd:$("#myd").combobox("getValue"),
        createDate:$("#time").datebox("getValue"),
    });
}
function openCustomerServeAssignDialog () {
    //选择一条数据进行分配
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择一条记录进行分配!");
        return ;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","只能选择一条记录进行分配!");
        return ;
    }
    $("#dlg").dialog("open");
    //加载数据进表格
    $("#fm").form('load',rows[0])
}
function addCustomerServeAssign () {
    saveOrUpdateData("fm",ctx+"/customerServe/saveOrUpdateServe","dlg",function () {
        $("#dg").datagrid('load');
    })
}
function openCustomerServeProceDialog () {
    //选择一条数据进行分配
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择一条记录进行处理!");
        return ;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","只能选择一条记录进行处理!");
        return ;
    }
    $("#dlg").dialog("open");
    //加载数据进表格
    $("#fm").form('load',rows[0])
}
function addCustomerServeProce () {
    saveOrUpdateData("fm",ctx+"/customerServe/saveOrUpdateServe","dlg",function () {
        $("#dg").datagrid('load');
    })
}
function openCustomerServeFeedBackDialog () {
    //选择一条数据进行分配
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择一条记录进行处理!");
        return ;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","只能选择一条记录进行处理!");
        return ;
    }
    $("#dlg").dialog("open");
    //加载数据进表格
    $("#fm").form('load',rows[0])
}
function addCustomerServeFeedBack () {
    saveOrUpdateData("fm",ctx+"/customerServe/saveOrUpdateServe","dlg",function () {
        $("#dg").datagrid('load');
    })
}