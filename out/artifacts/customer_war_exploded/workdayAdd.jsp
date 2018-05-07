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
    <title>新增工作周报信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        function check() {
            if(form1.weekDate.value==""){
                alert("请输入日期");
                form1.weekDate.focus();
                return false;
            }
            if(form1.workContent.value==""){
                alert("请输入周工作内容");
                form1.workContent.focus();
                return false;
            }
            if(form1.workReview.value==""){
                alert("请输入周工作总结");
                form1.workReview.focus();
                return false;
            }
            if(form1.question.value==""){
                alert("请输入存在问题");
                form1.question.focus();
                return false;
            }
            if(form1.weekPlan.value==""){
                alert("请输入下周计划");
                form1.weekPlan.focus();
                return false;
            }
            if(form1.userid.value==""){
                alert("请输入操作人");
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
            location.href="${pageContext.request.contextPath }/workdayservlet";
        }
    </script>
</head>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>workdayservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <center>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>新增工作周报信息</legend>
        </fieldset>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">日期</div>
            <div class="layui-input-inline">
                <input type="text" name="weekDate" id="weekDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">周工作内容</div>
            <div class="layui-input-inline">
                <input  type="text" name="workContent" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入周工作内容">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">周工作总结</div>
            <div class="layui-input-inline">
                <input  type="text" name="workReview" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入周工作总结">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">存在问题</div>
            <div class="layui-input-inline">
                <input  type="text" name="question" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入存在问题">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">急需解决问题</div>
            <div class="layui-input-inline">
                <input  type="text" name="warning" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入急需解决问题">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 390px;">
            <div class="layui-form-label">下周计划</div>
            <div class="layui-input-inline">
                <input  type="text" name="weekPlan" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入下周计划">
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
            elem: '#oprtime'
        });
        //日期
        laydate.render({
            elem: '#weekDate'
        });
    });
</script>
</body>
</html>
