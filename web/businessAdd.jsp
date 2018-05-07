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
    <title>新增商务洽谈资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        function check() {
            if(form1.busDate.value==""){
                alert("请输入日期");
                form1.busDate.focus();
                return false;
            }
            if(form1.prodName.value==""){
                alert("请输入商品信息");
                form1.prodName.focus();
                return false;
            }
            if(form1.chatContent.value==""){
                alert("请输入洽谈内容");
                form1.chatContent.focus();
                return false;
            }
            if(form1.chatResult.value==""){
                alert("请输入注洽谈情况");
                form1.chatResult.focus();
                return false;
            }
            if(form1.custContact.value==""){
                alert("请输入客户联系人");
                form1.custContact.focus();
                return false;
            }
            if(form1.phone.value==""){
                alert("请输入客户电话");
                form1.phone.focus();
                return false;
            }
            if(form1.moduleMoney.value==""){
                alert("请输入报价金额");
                form1.moduleMoney.focus();
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/businessservlet";
        }
    </script>
</head>
<body>
<h2 align="新增商务洽谈资料"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>businessservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>新增商务洽谈资料</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 260px">
            <div class="layui-form-label">日期</div>
            <div class="layui-input-inline">
                <input type="text" name="busDate" id="busDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>

            <div class="layui-form-label" style="margin-left: 20px">商品信息</div>
            <div class="layui-input-inline">
                <select name="prodName"  style="width:300px">
                    <c:forEach items="${productList}" var="s">
                        <option value="${s.prodname}">${s.prodname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">洽谈内容</label>
                <div class="layui-input-inline">
                    <input name="chatContent"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入洽谈内容" >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">洽谈情况</label>
                <div class="layui-input-inline">
                    <input name="chatResult"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入洽谈情况" >
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户名称</label>
                <div class="layui-input-inline">
                    <select name="custid"  style="width:300px">
                        <c:forEach items="${custList}" var="s">
                            <option value="${s.custId}">${s.custname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户联系人</label>
                <div class="layui-input-inline">
                    <input type="text" name="custContact"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入客户联系人">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入客户电话">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">员工信息</label>
                <div class="layui-input-inline">
                    <select name="userid"  style="width:300px">
                        <c:forEach items="${userList}" var="s">
                            <option value="${s.userid}">${s.username}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">报价模块</label>
                <div class="layui-input-inline">
                    <input type="text" name="module" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入报价模块">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">报价情况</label>
                <div class="layui-input-inline">
                    <input type="text" name="moduleState"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入报价情况">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">报价金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="moduleMoney" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入报价金额">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">

                </div>
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
            elem: '#busDate'
        });
    });
</script>
</body>
</html>
