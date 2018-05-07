<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.PageObject" %>
<%@ page import="bean.finance" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>开票信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <style type="text/css">
        #list{
            margin-top: 140px;
        }
        #list #form #text{
            width: 45px;
            height: 29px;
            border: 1px solid #ccc;
            text-align: center;
            margin-left: 7px;
            margin-right:7px;
        }
        #list #form{
            margin-left: 370px;
            margin-top: -30px;
        }
    </style>
    <script src = "jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function del(){
            if(confirm('删除确认')){
                return true
            }else{
                return false
            };
        }

        $(function() {
            $(".subBox").click(function () {
                $("input[name='subBox']").each(function () {
                    if($("input[name='subBox']").not("input:checked")){
                        $("#checkAll").attr("checked",false);
                    }
                })
            })
            //用于检查是否选中,选中的话提示值
            $("#butt").click(function () {
                var arrChk = $("input[name='subBox']:checked");
                $(arrChk).each(function () {
                    window.location.href="<%=basePath%>ticketservlet?action=del&id="+this.value;
                });
                if (arrChk.length == 0) {
                    alert("没有选中")
                }
            });

            //修改
            $("#updatebutt").click(function () {
                var arrChk = $("input[name='subBox']:checked");
                if (arrChk.length == 0) {
                    alert("没有选中");
                    return;
                }
                if(arrChk.length > 1){
                    alert("只能选择一个");
                    return;
                }
                $(arrChk).each(function () {
                    window.location.href="<%=basePath%>ticketservlet?action=update&id="+this.value;

                });
            });
        });
        function reload() {
            var val = $("#demoReload").val();
            window.location.href = "<%=basePath%>ticketservlet?&OrderId="+val;
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>开票信息</legend>
</fieldset>
<div class="demoTable" style="text-align: center">
    搜索订单号：
    <div class="layui-inline">
        <input class="layui-input" name="id" value="${OrderId}" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload" onclick="reload();">搜索</button>
</div>
<a href="<%=basePath%>ticketservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">编号</td>
            <td align="center">开票日期</td>
            <td align="center">订单号</td>
            <td align="center">客户名称</td>
            <td align="center">开票金额</td>
            <td align="center">出票公司</td>
            <td align="center">用户名称</td>
            <td align="center">操作时间</td>
            <td align="center">备注</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${TicketList}" var = "p">
            <tr>
                <td align="center"><input name="subBox" id="lists" type="checkbox" lay-skin="primary" value="${p.id}" /></td>
                <td align="center">${p.ticketDate}</td>
                <td align="center">${p.orderid}</td>
                <td align="center">${p.custname}</td>
                <td align="center">${p.ticketMoney}</td>
                <td align="center">${p.ticketComp}</td>
                <td align="center">${p.username}</td>
                <td align="center">${p.oprtime}</td>
                <td align="center">${p.remark}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id = "list">
        <%
            PageObject pageObject = (PageObject)request.getAttribute("pager");
        %>
        <div class="layui-form-label">
            当前页：<%=pageObject.getCurPage() %>/<%=pageObject.getPageCount() %>
        </div>
        <a href="<%=basePath%>ticketservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>ticketservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>ticketservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>ticketservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>ticketservlet" method = "post" name="form1">
                <input type="text" id = "text" name = "val">
                <input type="submit" class="layui-btn layui-btn-small" value="GO">
            </form>
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="page/user/allUsers.js"></script>
</html>
