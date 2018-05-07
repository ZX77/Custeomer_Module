<%--
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
    <title>新增开票信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        function check() {
            if(form1.ticketDate.value==""){
                alert("请输入开票日期");
                form1.ticketDate.focus();
                return false;
            }
            if(form1.orderid.value==""){
                alert("请输入订单号");
                form1.orderid.focus();
                return false;
            }
            if(form1.ticketMoney.value==""){
                alert("请输入开票金额");
                form1.ticketMoney.focus();
                return false;
            }
            if(form1.ticketComp.value==""){
                alert("请输入出票公司");
                form1.ticketComp.focus();
                return false;
            }
            if(form1.userid.value==""){
                alert("请输入用户名");
                form1.userid.focus();
                return false;
            }
            if(form1.oprtime.value==""){
                alert("请输入操作时间");
                form1.oprtime.focus();
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/ticketservlet";
        }
    </script>
</head>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>ticketservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <center>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>新增开票信息</legend>
        </fieldset>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">开票日期</div>
            <div class="layui-input-inline">
                <input type="text" name="ticketDate" id="ticketDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">订单号</div>
            <div class="layui-input-inline">
                <input  type="text" name="orderid" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入订单号">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">客户编号</div>
            <div class="layui-input-inline">
                <select name="custid"  style="width:300px">
                    <c:forEach items="${custList}" var="s">
                        <option value="${s.custId}">${s.custname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">开票金额</div>
            <div class="layui-input-inline">
                <input  type="text" name="ticketMoney" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入开票金额">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">出票公司</div>
            <div class="layui-input-inline">
                <input  type="text" name="ticketComp" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入出票公司">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">操作时间</div>
            <div class="layui-input-inline">
                <input type="text" name="oprtime" id="oprtime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                备注
            </label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" placeholder = "备注" id = "remark" name = "remark"></textarea>
            </div>
        </div>
        <input type="submit" value=" 保 存 " class="layui-btn">
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
            elem: '#ticketDate'
        });
        //日期
        laydate.render({
            elem: '#oprtime'
        });
    });
</script>
</body>
</html>
