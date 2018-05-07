<%@ page import="bean.PageObject" %><%--
  Created by IntelliJ IDEA.
  User: 林longqi
  Date: 2018/3/23
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <script src = "jquery/jquery-3.3.1.js"></script>
    <style type="text/css">
        #list{
            margin-top: 100px;
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
    </style>
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
                    var url = "<%=basePath%>supplierservlet";
                    window.location.href="<%=basePath%>supplierservlet?action=del&supplierId="+this.value;
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
                    window.location.href="<%=basePath%>supplierservlet?action=update&supplierId="+this.value;

                });
            });
        });

    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>供应商信息</legend>
</fieldset>
<a href="<%=basePath%>supplierservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">编号</td>
            <td align="center">名称</td>
            <td align="center">银行账号</td>
            <td align="center">开户银行</td>
            <td align="center">联系人</td>
            <td align="center">电话</td>
            <td align="center">地址</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tobody>
        <c:forEach items="${supplierList}" var = "s">
        <tr>
            <td align="center"><input name="subBox" type="checkbox" lay-skin="primary" value="${s.supplierId}" /></td>
            <td align="center">${s.supplierName}</td>
            <td align="center">${s.bankAccount}</td>
            <td align="center">${s.bankName}</td>
            <td align="center">${s.contact}</td>
            <td align="center">${s.phone}</td>
            <td align="center">${s.addr}</td>
            <td align="center">${s.remark}</td>
        </tr>
        </c:forEach>
        </tobody>
    </table>
    <div id = "list">
        <%
            PageObject pageObject = (PageObject)request.getAttribute("pager");
        %>
        <div class="layui-form-label">
            当前页：<%=pageObject.getCurPage() %>/<%=pageObject.getPageCount() %>
        </div>

        <a href="<%=basePath%>supplierservlet?opr=first&curPage=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>supplierservlet?opr=prior&curPage=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>supplierservlet?opr=next&curPage=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>supplierservlet?opr=last&curPage=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>supplierservlet" method = "post" name="form1" onsubmit="return check();">
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
