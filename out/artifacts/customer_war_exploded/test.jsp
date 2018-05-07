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
<h2 align="center">客户：${custname}的相关信息</h2>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>客户订单详情</legend>
    </fieldset>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">订单编号</td>
            <td align="center">客户名称</td>
            <td align="center">业务员</td>
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
            <c:forEach items="${ordersList}" var = "s">
                <tr>
                    <td align="center">${s.orderId}</td>
                    <td align="center">${s.customername}</td>
                    <td align="center">${s.username}</td>
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
    <br/><br/><br/>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>客户合同详情</legend>
    </fieldset>
    <div class="layui-form">
        <table class="layui-table ">
            <thead>
            <tr>
                <td align="center">客户名称</td>
                <td align="center">合同签订时间</td>
                <td align="center">合同到期时间</td>
                <td align="center">合同金额</td>
                <td align="center">支付类别</td>
                <td align="center">合同状态</td>
                <td align="center">业务员</td>
                <td align="center">操作员</td>
                <td align="center">操作时间</td>
            </tr>
            </thead>
            <tobody>
                <c:forEach items="${contractList}" var = "s">
                    <tr>
                        <td align="center">${s.customer}</td>
                        <td align="center">${s.contract_time}</td>
                        <td align="center">${s.due_time}</td>
                        <td align="center">${s.total_money}</td>
                        <td align="center">${s.pay_type}</td>
                        <td align="center">${s.status}</td>
                        <td align="center">${s.username}</td>
                        <td align="center">${s.operator}</td>
                        <td align="center">${s.oprtime}</td>
                    </tr>
                </c:forEach>
            </tobody>
        </table>
    </div>
    <br/><br/><br/>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>客户派工单详情</legend>
    </fieldset>
    <div class="layui-form">
        <table class="layui-table ">
            <thead>
            <tr>
                <td align="center">商品信息</td>
                <td align="center">客户名称</td>
                <td align="center">派工内容</td>
                <td align="center">派工完成情况</td>
                <td align="center">员工信息</td>
                <td align="center">客户评价</td>
                <td align="center">客户签名</td>
                <td align="center">开工时间</td>
                <td align="center">结束时间</td>
                <td align="center">人工天数</td>
                <td align="center">交通费</td>
                <td align="center">补助费</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jobRecordList}" var = "p">
                <tr>
                    <td align="center">${p.prodinfor}</td>
                    <td align="center">${p.custname }</td>
                    <td align="center">${p.jobContent }</td>
                    <td align="center">${p.callback }</td>
                    <td align="center">${p.username }</td>
                    <td align="center">${p.custEval }</td>
                    <td align="center">
                        <c:if test="${p.custSign==1}">已签</c:if>
                        <c:if test="${p.custSign==0}">未签</c:if>
                    </td>
                    <td align="center">${p.startTime }</td>
                    <td align="center">${p.endTime }</td>
                    <td align="center">${p.workDay }</td>
                    <td align="center">${p.busMoney }</td>
                    <td align="center">${p.attachMoney }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br/><br/><br/>

<fieldset class="layui-elem-field layui-field-title">
    <legend>客户商务洽谈详情</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">商品信息</td>
            <td align="center">洽谈内容</td>
            <td align="center">洽谈情况</td>
            <td align="center">客户名称</td>
            <td align="center">客户联系人</td>
            <td align="center">客户电话</td>
            <td align="center">员工信息</td>
            <td align="center">报表模块</td>
            <td align="center">报价情况</td>
            <td align="center">报价金额</td>
            <td align="center">备注</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${businessList}" var = "p">
            <tr>
                <td align="center">${p.prodName}</td>
                <td align="center">${p.chatContent}</td>
                <td align="center">${p.chatResult }</td>
                <td align="center">${p.custname}</td>
                <td align="center">${p.custContact }</td>
                <td align="center">${p.phone }</td>
                <td align="center">${p.username }</td>
                <td align="center">${p.module}</td>
                <td align="center">${p.moduleState }</td>
                <td align="center">${p.moduleMoney }</td>
                <td align="center">${p.remark }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script src="layui/layui/layui.js" charset="utf-8"></script>
</body>
</html>
