
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>表格</title>
    <link rel="stylesheet" href="layui/layui/css/layui.css" media="all">
    <style>
        body{margin: 10px;}
    </style>
</head>
<body>
<div class="demoTable">
    搜索ID：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>
<table class="layui-hide" id="test" lay-filter="demo"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">新增</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="layui/layui/layui.js"></script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table'], function(){
        var laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            ,table = layui.table //表格

        //执行一个 table 实例
        table.render({
            elem: '#test'
            ,height: 332
            ,url: '<%=basePath%>unitservlet' //数据接口
            ,page: true //开启分页
            ,align:'center'
            ,width:600
            ,cols: [[ //表头
                {field: 'unitId', title: '单位ID', sort: true, fixed: 'left'}
                ,{field: 'unitName', title: '单位名称'}
                ,{fixed: 'right', width: 200, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href = "<%=basePath%>unitservlet?action=init";
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(){
                    window.location.href = "<%=basePath%>unitservlet?action=del&unitId="+data.unitId;
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
                window.location.href = "<%=basePath%>unitservlet?action=update&unitId="+data.unitId;
            }
        });
    });
</script>
</body>
</html>
