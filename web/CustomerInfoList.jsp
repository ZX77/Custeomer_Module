<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.PageObject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>客户资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <script type="text/javascript" src="layui/layui.js"></script>
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
            margin-left: 345px;
            margin-top: -30px;
        }
        .panel{
            margin-left: 140px;
            margin-top:-29px
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
                    var url = "<%=basePath%>customerinfoservlet";
                    window.location.href="<%=basePath%>customerinfoservlet?action=del&custId="+this.value;
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
                    window.location.href="<%=basePath%>customerinfoservlet?action=update&custId="+this.value;

                });
            });
        });
        function reload() {
            var custname = $("#demoReload").val();
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.open({
                    type: 2,
                    title:'客户基本资料',
                    area:['1200px', '500px'],
                    content: '<%=basePath%>shortinfor?custname='+custname
                });
            });
        }
        
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>客户资料</legend>
</fieldset>
<div class="demoTable" style="text-align: center">
    搜索客户相关信息：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload" onclick="reload();">搜索</button>
</div>
<a href="<%=basePath%>customerinfoservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align = "center">客户编号</td>
            <td align="center">客户名称</td>
            <td align="center">行业</td>
            <td align="center">银行账号</td>
            <td align="center">联系人</td>
            <td align="center">电话</td>
            <td align="center">开票名称</td>
            <td align="center">开票地址</td>
            <td align="center">开票电话</td>
            <td align="center">纳税登记号</td>
            <td align="center">客户状态</td>
            <td align="center">业务员</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${CustomerInfoList}" var = "p">
            <tr>
                <td align = "center"><input name="subBox" type="checkbox" lay-skin="primary" value="${p.custId}" /></td>
                <td align="center">${p.custname}</td>
                <td align="center">${p.custtype}</td>
                <td align="center">${p.bankAccount}</td>
                <td align="center">${p.contact}</td>
                <td align="center">${p.phone}</td>
                <td align="center">${p.ticketName}</td>
                <td align="center">${p.ticketAddr}</td>
                <td align="center">${p.ticketTel}</td>
                <td align="center">${p.taxNo}</td>
                <td align="center">${p.custState}</td>
                <td align="center">${p.users}</td>
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
        <a href="<%=basePath%>customerinfoservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>customerinfoservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>customerinfoservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>customerinfoservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>customerinfoservlet" method = "post" name="form1">
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
