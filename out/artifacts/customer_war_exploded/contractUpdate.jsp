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
    <title>修改合同信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script>
        function back() {
            location.href="${pageContext.request.contextPath }/contractservlet";
        }
    </script>
</head>
<body>
<h2 align="修改合同表"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="${pageContext.request.contextPath}/contractservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <input type="hidden" name="contract_id" value="${contract.contract_id}">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改合同资料</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 250px">
            <div class="layui-form-label">
                合同编号:
            </div>
            <div class="layui-input-inline">
                <input name="contract_no" value="${contract.contract_no}"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入商品编号">
            </div>

            <div class="layui-form-label" style="margin-left: 20px">
                客户名称:
            </div>
            <div class="layui-input-inline">
                <select name = "custId">
                    <c:forEach items="${customerInfoListList}" var="d">
                        <c:if test="${contract.custId==d.custId}">
                            <option value="${d.custId}" selected>${d.custname}</option>
                        </c:if>
                        <c:if test="${contract.custId!=d.custId}">
                            <option value="${d.custId}">${d.custname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 250px">
            <div class="layui-form-label" style="width: 90px">
                合同签订时间:
            </div>
            <div class="layui-input-inline">
                <input type="text" name="contract_time" value="${contract.contract_time}" id="contract_time" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>

            <div class="layui-form-label" style="margin-left: 20px;width: 90px">
                合同到期时间:
            </div>
            <div class="layui-input-inline">
                <input type="text" name="due_time" value="${contract.due_time}" id="due_time" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 250px">
            <div class="layui-form-label">
                合同金额:
            </div>
            <div class="layui-input-inline">
                <input name="total_money" value="${contract.total_money}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入商品编号">
            </div>

            <div class="layui-form-label" style="margin-left: 20px">
                合同有效期:
            </div>
            <div class="layui-input-inline">
                <input type="text" name="deposit" value="${contract.deposit}" id="deposit" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 250px">
            <div class="layui-form-label">
                支付类别:
            </div>
            <div class="layui-input-inline">
                <select name="pay_type" value="${contract.pay_type}">
                    <option value=季度支付>季度支付</option>
                    <option value=月度支付>月度支付</option>
                    <option value=按半年支付>按半年支付</option>
                    <option value=按年支付>按年支付</option>
                </select>
            </div>

            <div class="layui-form-label" style="margin-left: 20px">
                合同状态:
            </div>
            <div class="layui-input-inline">
                <select name="status" value="${contract.status}">
                    <option value=已签订 selected>已签订</option>
                    <option value=已支付>已支付</option>
                    <option value=已完成>已完成</option>
                    <option value=服务中>服务中</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 250px">
            <div class="layui-form-label">
                业务员:
            </div>
            <div class="layui-input-inline">
                <select name = "empid">
                    <c:forEach items="${usersList}" var="d">
                        <c:if test="${contract.empid==d.userid}">
                            <option value="${d.userid}" selected>${d.username}</option>
                        </c:if>
                        <c:if test="${contract.empid!=d.userid}">
                            <option value="${d.userid}">${d.username}</option>
                        </c:if>
                    </c:forEach>
                </select>
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
            elem: '#contract_time'
        });
        laydate.render({
            elem: '#due_time'
        });
        laydate.render({
            elem: '#deposit'
        });
    });
</script>
</body>
</html>
