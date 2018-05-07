c<%--
  Created by IntelliJ IDEA.
  User: 林longqi
  Date: 2018/3/23
  Time: 15:57
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
    <title>修改订单明细</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
</head>
<body>
<h2 align="修改订单明细"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;">
<form action="<%=basePath%>orderdetailservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改订单明细</legend>
    </fieldset>
    <center>
    <input type="hidden" name="detailId" value="${order.detailId}">
        <div class="layui-form-item" style="margin-left: 260px;">
            <label class="layui-form-label">订单编号</label>
            <div class="layui-input-inline">
                <input name="orderId"  type="text" value="${order.orderId}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入销售金额">
            </div>
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-inline">
                <select name = "prodid">
                    <c:forEach items="${productList}" var="d">
                        <c:if test="${order.prodid==d.prodid}">
                            <option value="${d.prodid}" selected>${d.prodname}</option>
                        </c:if>
                        <c:if test="${order.prodid!=d.prodid}">
                            <option value="${d.prodid}">${d.prodname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item"  style="margin-left: 265px;">
        <label class="layui-form-label">销售类别</label>
            <div class="layui-input-inline">
                <select name="status">
                    <c:if test="${order.status==1}">
                        <option value="1" selected>销售</option>
                        <option value="2">赠送</option>
                        <option value="3">配套</option>
                    </c:if>
                    <c:if test="${order.status==2}">
                        <option value="1">销售</option>
                        <option value="2" selected>赠送</option>
                        <option value="3">配套</option>
                    </c:if>
                    <c:if test="${order.status==3}">
                        <option value="1">销售</option>
                        <option value="2">赠送</option>
                        <option value="3" selected>配套</option>
                    </c:if>
                </select>
            </div>
                <label class="layui-form-label">销售金额</label>
                <div class="layui-input-inline">
                    <input name="saleMoney"  type="text" value="${order.saleMoney}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入销售金额" >
                </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">单位</label>
                <div class="layui-input-inline">
                    <select name = "unitId">
                        <c:forEach items="${unitList}" var="d">
                            <c:if test="${order.unitId==d.unitId}">
                                <option value="${d.unitId}" selected>${d.unitName}</option>
                            </c:if>
                            <c:if test="${order.unitId!=d.unitId}">
                                <option value="${d.unitId}">${d.unitName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">注册联系人</label>
                <div class="layui-input-inline">
                    <input type="text" name="regPerson" value="${order.regPerson}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入注册联系人">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">注册密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="regPassword" value="${order.regPassword}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入注册密码">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">服务期限</label>
                <div class="layui-input-inline">
                    <input type="text" name="servicePeriod" value="${order.servicePeriod}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入服务期限">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">服务到期日</label>
                <div class="layui-input-inline">
                    <input type="text" name="expireDate" value="${order.expireDate}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入服务到期日">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">销售数量</label>
                <div class="layui-input-inline">
                    <input type="text" name="prodCount" value="${order.prodCount}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入销售数量">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">总金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="totalMoney" value="${order.totalMoney}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入总金额">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开票时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="oprtime" value="${order.oprtime}" id="oprtime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                描述
            </label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" placeholder = "请说明描述" id = "remark" name = "remark"></textarea>
            </div>
        </div>
        <input type="submit" class="layui-btn" value="保存">
    </center>
</form>
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
</body>
</html>
