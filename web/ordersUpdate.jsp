
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改订单</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script>
        function back() {
            location.href="${pageContext.request.contextPath }/ordersservlet";
        }
    </script>
</head>
<body>
<h2 align="修改订单表"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="${pageContext.request.contextPath}/ordersservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="">

    <input type="hidden" name = "orderId" value="${orders.orderId}">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改订单资料</legend>
    </fieldset>

    <center>
    <div class="layui-form-item" style="margin-left: 250px">
        <div class="layui-form-label">
            客户:
        </div>
        <div class="layui-input-inline">
            <select name="custid">
            <c:forEach items="${customerInfoList}" var="d">
                <c:if test="${orders.custid==d.custId}">
                    <option value="${d.custId}" selected>${d.custname}</option>
                </c:if>
                <c:if test="${orders.custid!=d.custId}">
                    <option value="${d.custId}">${d.custname}</option>
                </c:if>
            </c:forEach>
            </select>
        </div>

        <div class="layui-form-label" style="margin-left: 20px">
            业务员:
        </div>
        <div class="layui-input-inline">
            <select name="userid">
                <c:forEach items="${usersList}" var="d">
                    <c:if test="${orders.userid==d.userid}">
                        <option value="${d.userid}" selected>${d.username}</option>
                    </c:if>
                    <c:if test="${orders.userid!=d.userid}">
                        <option value="${d.userid}">${d.username}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 250px">
        <div class="layui-form-label">
            订单类别:
        </div>
        <div class="layui-input-inline">
            <select name="orderType" style="width:400px" value="${orders.orderType}">
                <c:if test="${orders.orderType=='采购入库'}">
                    <option value="采购入库" selected>采购入库</option>
                    <option value="销售入库">销售入库</option>
                </c:if>
                <c:if test="${orders.orderType!='采购入库'}">
                    <option value="采购入库" >采购入库</option>
                    <option value="销售入库" selected>销售入库</option>
                </c:if>
            </select>
        </div>

        <div class="layui-form-label" style="margin-left: 20px">
            订单状态:
        </div>
        <div class="layui-input-inline">
            <select name="orderStatus" value="${orders.orderStatus}">
                <option value=已出货>已出货</option>
                <option value=已收款>已收款</option>
                <option value=实施中>实施中</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 250px">
        <div class="layui-form-label">
            进度:
        </div>
        <div class="layui-input-inline">
            <input name="process" value="${orders.process}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入进度">
        </div>

        <div class="layui-form-label" style="margin-left: 20px">
            订单金额:
        </div>
        <div class="layui-input-inline">
            <input name="totalMoney" value="${orders.totalMoney}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入订单金额" >
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 250px">
        <div class="layui-form-label">
            开票时间:
        </div>
        <div class="layui-input-inline">
            <input type="text" name="oprtime" id="oprtime" value="${orders.oprtime}" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>

        <div class="layui-form-label" style="margin-left: 20px">
            开票人:
        </div>
        <div class="layui-input-inline">
            <input name="operator" value="${orders.operator}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入开票人" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-form-label">
            描述:
        </div>
        <div class="layui-input-block">
            <textarea class="layui-textarea" placeholder = "请说明描述" id = "remark" name = "remark">${orders.remark}</textarea>
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
            elem: '#oprtime'
        });
    });
</script>
</form>
</body>
</html>
