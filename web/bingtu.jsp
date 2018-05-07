<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <link rel="stylesheet" href="<%=basePath%>shux/xadmin.css">
    <link rel="stylesheet" href="<%=basePath%>shux/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="<%=basePath%>shux/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>shux/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="<%=basePath%>shux/lib/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>shux/xadmin.js"></script>
</head>
<body>
<div class="page-content">
    <div class="content">
        <blockquote class="layui-elem-quote">
        </blockquote>
        <div id="main" style="width: 100%;height:400px;"></div>
        <blockquote class="layui-elem-quote">
        </blockquote>
    </div>
</div>
</div>
<script src="<%=basePath%>shux/echarts/3.3.2/echarts.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>shux/echarts-for-x-admin.js"></script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'),'echarts-for-x-admin');
    var option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['王敏', '何明', '科威', '陈龙', '王可'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'订单数据金额',
                type:'bar',
                barWidth: '60%',
                data:[${count1},${count2},${count3},${count4},${count5}]
            }
        ]
    };
    myChart.setOption(option);
</script>


</body>
</html>