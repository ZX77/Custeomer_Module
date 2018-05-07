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
    <script>
    function back() {
        location.href="${pageContext.request.contextPath }/contractattachservlet";
    }
    </script>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>
<body>
<h2 align="修改合同表"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="${pageContext.request.contextPath}/contractattachservlet?action=updateSave" class="layui-form" method="post" enctype="multipart/form-data" name="form1" onsubmit="return check();">
    <input type="hidden" name="contractAttach_id" value="${contractAttach.contractAttach_id}">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改合同附件表</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                合同编号:
            </div>
            <div class="layui-input-inline">
                <select name = "contract_id">
                    <c:forEach items="${contractList}" var="d">
                        <c:if test="${contractAttach.contract_id==d.contract_id}">
                            <option value="${d.contract_id}" selected>${d.contract_no}</option>
                        </c:if>
                        <c:if test="${contractAttach.contract_id!=d.contract_id}">
                            <option value="${d.contract_id}">${d.contract_no}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                排列号:
            </div>
            <div class="layui-input-inline">
                <input name="Seq" value="${contractAttach.seq}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入排列号">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                附件名称:
            </div>
            <div class="layui-input-inline">
                <input name="AttachFile" value="${contractAttach.attachFile}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入附件名称">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 440px">
            <div class="layui-form-label">
                附件地址:
            </div>
            <div class="layui-input-inline">
                <input name="attachURL" value="${contractAttach.attachURL}" type="file" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入附件地址">
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
    });
</script>
</body>
</html>
