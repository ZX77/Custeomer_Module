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
    <title>修改客户联系资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script type="text/javascript">
        function check() {
            if(form1.talkDate.value==""){
                alert("请输入日期");
                form1.talkDate.focus();
                return false;
            }
            if(form1.custContact.value==""){
                alert("请输入客户联系人");
                form1.custContact.focus();
                return false;
            }
            if(form1.phone1.value==""){
                alert("请输入手机号1");
                form1.phone1.focus();
                return false;
            }
            if(form1.qqCode.value==""){
                alert("请输入QQ好");
                form1.qqCode.focus();
                return false;
            }
            if(form1.weixin.value==""){
                alert("请输入微信号");
                form1.weixin.focus();
                return false;
            }
            if(form1.birthday.value==""){
                alert("请输入您的生日");
                form1.birthday.focus();
                return false;
            }
            if(form1.hobbit.value==""){
                alert("请输入喜爱");
                form1.hobbit.focus();
                return false;
            }
            if(form1.jobName.value==""){
                alert("请输入岗位");
                form1.jobName.focus();
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/contactservlet";
        }
    </script>
</head>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back()" class="layui-form" method="post" name="form1" onsubmit="return check();">
<h2 align="修改客户联系资料"></h2>
<form action="<%=basePath%>contactservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改客户联系资料</legend>
    </fieldset>
    <input type="hidden" name = "contactId" value = "${contact.contactId}">
    <center>
        <div class="layui-form-item" style="margin-left: 330px">
            <div class="layui-form-label">日期</div>
            <div class="layui-input-inline">
                <input type="text" name="talkDate" id="talkDate" value="${contact.talkDate}" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>

            <div class="layui-form-label" style="margin-left: 20px">客户联系人</div>
            <div class="layui-input-inline">
                <input name="custContact"  type="text" value="${contact.custContact}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入客户联系人信息" >
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机1</label>
                <div class="layui-input-inline">
                    <input name="phone1"  type="text" value="${contact.phone1}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入手机1" >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">手机2</label>
                <div class="layui-input-inline">
                    <input name="phone2"  type="text" value="${contact.phone2}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入手机2" >
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户名称</label>
                <div class="layui-input-inline">
                    <select name="custid"  style="width:300px">
                        <c:forEach items="${customerInfoList}" var="u">
                            <c:if test="${u.custId==contact.custid}">
                                <option value="${u.custId}" selected>${u.custname}</option>
                            </c:if>
                            <c:if test="${u.custId!=contact.custid}">
                                <option value="${u.custId}">${u.custname}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">QQ</label>
                <div class="layui-input-inline">
                    <input type="text" name="qqCode" value="${contact.qqCode}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入QQ">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" value="${contact.email}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入邮箱">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">微信</label>
                <div class="layui-input-inline">
                    <input type="text" name="weixin" value="${contact.weixin}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入微信号">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工信息</label>
                <div class="layui-input-inline">
                    <select name="userid"  style="width:300px">
                        <c:forEach items="${usersList}" var="u">
                            <c:if test="${u.userid==contact.userid}">
                                <option value="${u.userid}" selected>${u.username}</option>
                            </c:if>
                            <c:if test="${u.userid!=contact.userid}">
                                <option value="${u.userid}">${u.username}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input type="text" name="birthday" value="${contact.birthday}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入生日">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">喜爱</label>
                <div class="layui-input-inline">
                    <input type="text" name="hobbit" value="${contact.hobbit}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入喜爱">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">岗位</label>
                <div class="layui-input-inline">
                    <input type="text" name="jobName" value="${contact.jobName}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入岗位名">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                备注
            </label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" placeholder = "备注" id = "remark" name = "remark">${contact.remark}</textarea>
            </div>
        </div>
        <input type="submit" class="layui-btn" value="保存">
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
            elem: '#talkDate'
        });
    });
</script>
</body>
</html>
