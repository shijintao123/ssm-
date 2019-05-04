<html>
<head>
<#include "common.ftl" >
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="${ctx}/js/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    $(function () {
        var ctx="${ctx}";
        $.ajax({
            url:ctx+"/customer/echartByServer",
            dataType:"json",
            type:"post",
            success:function (result) {
                console.log(result);
                var level=[];
                var level2=[];
                $(result).each(function () {
                    level.push(this.name);
                    level2.push(this.value);
                });
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));

                // 指定图表的配置项和数据
                option = {
                    xAxis: {
                        type: 'category',
                        data: level
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: level2,
                        type: 'bar'
                    }]
                };


                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        })
    })
</script>
</body>
</html>