<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src = "jquery/jquery-3.3.1.js"></script>
</head>
<body>
<h1 align="center">员工：${username}的相关客户信息</h1>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>[已出货]的订单详情</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">业务员</td>
            <td align="center">订单编号</td>
            <td align="center">客户名称</td>
            <td align="center">订单类别</td>
            <td align="center">订单状态</td>
            <td align="center">进度</td>
            <td align="center">订单金额</td>
            <td align="center">开票时间</td>
            <td align="center">开票人</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tobody>
            <c:forEach items="${ordersList1}" var = "s">
                <tr>
                    <td align="center">${s.username}</td>
                    <td align="center">${s.orderId}</td>
                    <td align="center">${s.customername}</td>
                    <td align="center">${s.orderType}</td>
                    <td align="center">${s.orderStatus}</td>
                    <td align="center">${s.process}</td>
                    <td align="center">${s.totalMoney}</td>
                    <td align="center">${s.oprtime}</td>
                    <td align="center">${s.operator}</td>
                    <td align="center">${s.remark}</td>
                </tr>
            </c:forEach>
        </tobody>
    </table>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>[实施中]的订单详情</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">业务员</td>
            <td align="center">订单编号</td>
            <td align="center">客户名称</td>
            <td align="center">订单类别</td>
            <td align="center">订单状态</td>
            <td align="center">进度</td>
            <td align="center">订单金额</td>
            <td align="center">开票时间</td>
            <td align="center">开票人</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tobody>
            <c:forEach items="${ordersList2}" var = "s">
                <tr>
                    <td align="center">${s.username}</td>
                    <td align="center">${s.orderId}</td>
                    <td align="center">${s.customername}</td>
                    <td align="center">${s.orderType}</td>
                    <td align="center">${s.orderStatus}</td>
                    <td align="center">${s.process}</td>
                    <td align="center">${s.totalMoney}</td>
                    <td align="center">${s.oprtime}</td>
                    <td align="center">${s.operator}</td>
                    <td align="center">${s.remark}</td>
                </tr>
            </c:forEach>
        </tobody>
    </table>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>[已收款]的订单详情</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">业务员</td>
            <td align="center">订单编号</td>
            <td align="center">客户名称</td>
            <td align="center">订单类别</td>
            <td align="center">订单状态</td>
            <td align="center">进度</td>
            <td align="center">订单金额</td>
            <td align="center">开票时间</td>
            <td align="center">开票人</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tobody>
            <c:forEach items="${ordersList3}" var = "s">
                <tr>
                    <td align="center">${s.username}</td>
                    <td align="center">${s.orderId}</td>
                    <td align="center">${s.customername}</td>
                    <td align="center">${s.orderType}</td>
                    <td align="center">${s.orderStatus}</td>
                    <td align="center">${s.process}</td>
                    <td align="center">${s.totalMoney}</td>
                    <td align="center">${s.oprtime}</td>
                    <td align="center">${s.operator}</td>
                    <td align="center">${s.remark}</td>
                </tr>
            </c:forEach>
        </tobody>
    </table>
</div>


<script src="layui/layui/layui.js" charset="utf-8"></script>
</body>
</html>
