<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增合同附件表</title>
    <script>
        function check() {
            var a = form1.Seq.value;
            if(isNaN(a)){
                alert("排序号需输入数字");
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/contractattachservlet";
        }
    </script>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>
<body>
<h2 align="新增合同附件表"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back()">
<form action="<%=basePath%>contractattachservlet?action=add" class="layui-form" method="post" name="form1"  enctype="multipart/form-data" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>新增合同附件表</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                排序号:
            </div>
            <div class="layui-input-inline">
                <input type="text" name="Seq" id="Seq" lay-verify="layui-input" placeholder="请输入排序号" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                附件名称:
            </div>
            <div class="layui-input-inline">
                <input name="AttachFile"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入附件名称">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                附件地址:
            </div>
            <div class="layui-input-inline">
                <input name="attachURL"  type="file" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入附件地址">
            </div>
        </div>

        <input type="submit" value=" 保 存 " class="layui-btn">
    </center>
</form>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script src="layui/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#contract_time'
        });
        laydate.render({
            elem: '#due_time'
        });
        laydate.render({
            elem: '#deposit'
        });
    });
</script>
</body>
</html>
