<%--
  Created by IntelliJ IDEA.
  User: 林longqi
  Date: 2018/3/23
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门新增</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>

<script type="text/javascript">
    function check() {
        if(form1.depName.value==""){
            alert("请输入部门名称");
            form1.depName.focus();
            return false;
        }
        return true;
    }
    function back() {
        location.href="${pageContext.request.contextPath }/depservlet";
    }
</script>
<body>

<h2 align="新增部门资料"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>depservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <center>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>部门新增</legend>
    </fieldset>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            部门名称:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="depname" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            负责人:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="chairman" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入负责人名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            部门描述:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="description" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门描述">
        </div>
    </div>
    <input type="submit" class="layui-btn" style="margin-left: -150px" value=" 保 存 ">
    </center>
</form>
</body>
</html>
