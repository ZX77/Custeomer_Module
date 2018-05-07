<%--
  Created by IntelliJ IDEA.
  User: 林longqi
  Date: 2018/3/23
  Time: 16:24
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
    <title>修改供应商信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script>
        function back() {
            location.href="${pageContext.request.contextPath }/supplierservlet";
        }
    </script>
</head>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back()">
<form action="${pageContext.request.contextPath}/supplierservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="">
    <center>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改供应商信息</legend>
    </fieldset>
    <input type="hidden" name="supplierId" value="${supplier.supplierId}">

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            供应商名称:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="supplierName" value="${supplier.supplierName}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入供应商名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            银行账号:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="bankAccount" value="${supplier.bankAccount}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入银行账号">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            开户银行:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="bankName" value="${supplier.bankName}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入开户银行">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            联系人:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="contact" value="${supplier.contact}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入联系人">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            电话:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="phone" value="${supplier.phone}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入电话">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            地址:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="addr" value="${supplier.addr}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入地址">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 390px;">
        <div class="layui-form-label">
            描述:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="remark" value="${supplier.remark}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入供应商描述">
        </div>
    </div>
    <input type="submit" value=" 保 存 " style="margin-left: -150px" class="layui-btn">
    </center>
</form>
</body>
</html>
