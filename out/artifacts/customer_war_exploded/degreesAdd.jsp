
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
    <title>新增岗位信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script type="text/javascript">
        function check() {
            if(form1.degreename.value==""){
                alert("请输入岗位名称");
                form1.degreename.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h2 align="新增岗位表"></h2>
<form action="<%=basePath%>degreesservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>新增岗位表</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 400px">
            <div class="layui-form-label">
                岗位:
            </div>
            <div class="layui-input-inline">
                <input name="degreename"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入岗位">
            </div>
        </div>
        <input type="submit" value=" 保 存 " class="layui-btn">
    </center>
</form>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
</body>
</html>
