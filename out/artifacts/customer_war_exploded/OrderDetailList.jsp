<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.PageObject" %>
<%@ page import="bean.OrderDetail" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>订单明细</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <style type="text/css">
        #list{
            margin-top: 140px;
        }
        #list #form #text{
            width: 45px;
            height: 29px;
            border: 1px solid #ccc;
            text-align: center;
            margin-left: 7px;
            margin-right:7px;
        }
        #list #form{
            margin-left: 370px;
            margin-top: -30px;
        }
    </style>
    <script src = "jquery/jquery-3.3.1.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>订单明细</legend>
</fieldset>





<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">商品名称</td>
            <td align="center">销售类别</td>
            <td align="center">销售金额</td>
            <td align="center">注册联系人</td>
            <td align="center">注册密码</td>
            <td align="center">服务期限</td>
            <td align="center">销售数量</td>
            <td align="center">总金额</td>
            <td align="center">开票时间</td>
            <td align="center">开票人</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${OrderDetailList}" var = "p">
            <tr>
                <td align="center">${p.prodname}</td>
                <td align="center">
                    <c:if test="${p.status==1}">销售</c:if>
                    <c:if test="${p.status==2}">赠送</c:if>
                    <c:if test="${p.status==3}">配套</c:if>
                </td>
                <td align="center">${p.saleMoney }</td>
                <td align="center">${p.regPerson }</td>
                <td align="center">${p.regPassword }</td>
                <td align="center">${p.expireDate}</td>
                <td align="center">${p.prodCount }</td>
                <td align="center">${p.totalMoney }</td>
                <td align="center">${p.oprtime }</td>
                <td align="center">${p.operator }</td>
                <td align="center">${p.remark }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="page/user/allUsers.js"></script>
</html>
