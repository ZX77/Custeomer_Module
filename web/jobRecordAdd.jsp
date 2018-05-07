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
    <title>新增派工单资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        function check() {
            if(form1.orderId.value==""){
                alert("请输入订单编号");
                form1.orderId.focus();
                return false;
            }
            if(form1.jobDate.value==""){
                alert("请输入日期");
                form1.jobDate.focus();
                return false;
            }
            if(form1.jobContent.value==""){
                alert("请输入派工内容");
                form1.jobContent.focus();
                return false;
            }
            if(form1.callback.value==""){
                alert("请输入派工完成情况");
                form1.callback.focus();
                return false;
            }
            if(form1.userid.value==""){
                alert("请输入于员工信息");
                form1.userid.focus();
                return false;
            }
            if(form1.custEval.value==""){
                alert("请输入客户评价");
                form1.custEval.focus();
                return false;
            }
            if(form1.startTime.value==""){
                alert("请输入开工时间");
                form1.startTime.focus();
                return false;
            }
            if(form1.endTime.value==""){
                alert("请输入结束时间");
                form1.endTime.focus();
                return false;
            }
            if(form1.busMoney.value==""){
                alert("请输入交通费");
                form1.busMoney.focus();
                return false;
            }
            if(form1.attachMoney.value==""){
                alert("请输入补助费");
                form1.attachMoney.focus();
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/jobrecordservlet";
        }
    </script>
</head>
<body>
<h2 align="新增派工单资料"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>jobrecordservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>新增派工单资料</legend>
    </fieldset>
    <center>
        <div class="layui-form-item" style="margin-left: 230px">
            <div class="layui-form-label" style="margin-left: 35px">订单编号</div>
                <div class="layui-input-inline">
                    <input name="orderId"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入订单编号" >
                </div>
                <label class="layui-form-label">日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="jobDate" id="jobDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品信息</label>
                <div class="layui-input-inline">
                    <select name="prodName"  style="width:300px">
                        <c:forEach items="${proList}" var="s">
                            <option value="${s.prodid}">${s.prodname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
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
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">派工内容</label>
                <div class="layui-input-inline">
                    <input type="text" name="jobContent"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入派工内容">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">完成情况</label>
                <div class="layui-input-inline">
                    <input type="text" name="callback" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入派工完成情况">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工信息</label>
                <div class="layui-input-inline">
                    <select name="userid"  style="width:300px">
                        <c:forEach items="${usersList}" var="s">
                            <option value="${s.userid}">${s.username}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户评价</label>
                <div class="layui-input-inline">
                    <input type="text" name="custEval" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入客户评价">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户签名</label>
                <div class="layui-input-inline">
                    <select name="custSign">
                        <option value="已签">已签</option>
                        <option value="未签">未签</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开工时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" id="startTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">结束时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" id="endTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">人工天数</label>
                <div class="layui-input-inline">
                    <input type="text" name="workDay"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入人工天数">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">交通费</label>
                <div class="layui-input-inline">
                    <input type="text" name="busMoney"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入交通费">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">补助费</label>
                <div class="layui-input-inline">
                    <input type="text" name="attachMoney"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入补助费">
                </div>
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
            elem: '#jobDate'
        });
        //日期
        laydate.render({
            elem: '#startTime'
        });        //日期
        laydate.render({
            elem: '#endTime'
        });
    });
</script>
</body>
</html>
