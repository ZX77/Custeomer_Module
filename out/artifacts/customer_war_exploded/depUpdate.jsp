<%--
  Created by IntelliJ IDEA.
  User: 林longqi
  Date: 2018/3/23
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>修改部门资料</title>
    <script type="text/javascript">
        function check(){
            if(form1.depname.value==""){
                alert("请输入部门名称");
                form1.depname.focus();
                return false;
            }
            return true;
        }
    </script>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    function back() {
        location.href="${pageContext.request.contextPath }/depservlet";
    }
</script>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back()">
<form action="<%=basePath%>depservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <center>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改部门资料</legend>
    </fieldset>
    <input type="hidden" name="depid" value="${dep.depid}">

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            部门名称:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="depname" class="layui-input" autocomplete="off" value="${dep.depname}" lay-verify="required" placeholder="请输入部门名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            负责人:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="chairman" value="${dep.chairman}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入负责人名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            部门描述:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="description" value="${dep.description}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门描述">
        </div>
    </div>
        <input type="submit" class="layui-btn" style="margin-left: -150px" value=" 保 存 ">
    </center>
</form>
</body>
</html>
