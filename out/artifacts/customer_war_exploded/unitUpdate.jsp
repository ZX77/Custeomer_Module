
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改单位信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script type="text/javascript">
        function check() {
            if(form1.unitName.value==""){
                alert("请输入单位名称");
                form1.unitName.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h2 align="修改单位表"></h2>
<form action="${pageContext.request.contextPath}/unitservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">

    <input type="hidden" name = "unitId" value="${unit.unitId}">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改单位</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 250px">
            <div class="layui-form-item" style="margin-left: 150px">
                <div class="layui-form-label">
                    单位:
                </div>
                <div class="layui-input-inline">
                    <input name="unitName" value="${unit.unitName}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入单位">
                </div>
            </div>
        </div>
        <input type="submit" value=" 保 存 " class="layui-btn">
    </center>
</form>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script src="layui/layui/layui.js" charset="utf-8"></script>
</form>
</body>
</html>
