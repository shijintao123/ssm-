function querySaleChancesByParams() {
    $('#dg').datagrid('load',{
        customerName:$("#customerName").val(),
        state:$("#state").combobox("getValue"),
        devResult:$("#devResult").combobox("getValue"),
        createDate:$("#time").datebox("getValue")
    });
}
$(function(){
    $('#dg').datagrid({
        rowStyler: function(index,row){
            if (row.devResult==0){
                return 'background-color:#6293BB;color:#fff;';
            }
            if (row.devResult==1){
                return "background-color:#f0ad4e;"; // 黄色
            }
            if (row.devResult==2){
                return "background-color:#5cb85c;"; // 绿色
            }
            if (row.devResult==3){
                return "background-color:#d9534f;"; // 红色
        }
    }
    });
    $("#dlg").dialog({
        "onClose":function () {
            $("#fm").form("clear");
        }
    })
});
function formatterState(value){
    if(value==1){
        return "已分配";
    }
    if(value=="0"){
        return "未分配";
    }
}
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
function openAddSaleChacneDialog (){
    $("#dlg").dialog("open");
}
function saveOrUpdateSaleChance (){
    saveOrUpdateData("fm",ctx+"/saleChance/addOrUpdateSaleChance","dlg",querySaleChancesByParams);//formId,saveOrUpdateUrl,dialogId,searchByParams
 /*   $("#fm").form('submit', {
        url: ctx+"/saleChance/addOrUpdateSaleChance",
        onSubmit: function(){
            //做表单校验
            return $(this).form('validate');
        },
        success: function(result){
            result=JSON.parse(result);
            if(result.code==200){
                $.messager.alert("来自crm",result.msg,"info",function () {
                        //清空表单
                        $("#fm").form("clear");
                        //关闭弹窗
                        $("#dlg").dialog("close");
                        //刷新数据
                        $("#dg").datagrid("load");
                })
            }else{
                $.messager.alert("来自crm",result.msg,"error");
            }
        }
    });*/
}
//修改
function openModifySaleChanceDialog(){
    openModifyDialog("dg","fm","dlg","更新营销机会");
  /* var rows= $("#dg").datagrid("getSelections");
   if(rows.length<1){
       $.messager.alert("来自crm","请至少选择一行进行更新","info");
       return;
   }
   if(rows.length>1){
       $.messager.alert("来自crm","只能选择一行进行更新","info");
       return;
   }
   $("#dlg").dialog("open");
    $("#fm").form("load",rows[0]);*/
}
//批量删除
function deleteSaleChance (){
    deleteData("dg",ctx+"/saleChance/deleteBatch",querySaleChancesByParams);
}
        //saleChance/deleteBatch
  /*  var rows= $("#dg").datagrid("getSelections");
    if(rows.length<1){
        $.messager.alert("来自crm","请至少选择一行进行更新","info");
        return;
    }
    if(rows.length>=1){
        $.messager.confirm("来自crm","确定要删除"+rows.length+"条数据吗？",function(r){
                    //循环便利所获得的Id
                    var ids="";
                    for(var i=0;i<rows.length;i++){
                        ids+="ids="+rows[i].id+"&";
                    }
                    console.log(ids);
                    if(r){
                        $.ajax({
                            url:ctx+"/saleChance/deleteBatch?"+ids,
                            type:"post",
                            dataType:"json",
                            success:function(result){
                                if(result.code==200){
                                    //删除成功 刷新表格
                                    $("#dg").datagrid("load");
                                }else{
                                    $.messager.alert("来自Crm",result.msg,"error");
                                }
                            }
                        });
                    }
        });
    }*/
