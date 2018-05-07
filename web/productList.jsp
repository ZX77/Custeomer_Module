<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.PageObject" %>
<%@ page import="bean.Product" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>商品信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <style type="text/css">
        #list{
            margin-top: 85px;
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
                    var url = "<%=basePath%>productservlet";
                    window.location.href="<%=basePath%>productservlet?action=del&prodid="+this.value;
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
                    window.location.href="<%=basePath%>productservlet?action=update&prodid="+this.value;

                });
            });
        });

    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商品信息</legend>
</fieldset>
<a href="<%=basePath%>productservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">编号</td>
            <td align="center">商品名</td>
            <td align="center">商品单位</td>
            <td align="center">规格</td>
            <td align="center">库存</td>
            <td align="center">进货价</td>
            <td align="center">销售价</td>
            <td align="center">最低价</td>
            <td align="center">条码</td>
            <td align="center">状态</td>
            <td align="center">供应商</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productList}" var = "p">
            <tr>
                <td align="center"><input name="subBox" type="checkbox" lay-skin="primary" value="${p.prodid }" /></td>
                <td align="center">${p.prodname }</td>
                <td align="center">${p.unit}</td>
                <td align="center">${p.prodStyle }</td>
                <td align="center">${p.prodCount }</td>
                <td align="center">${p.inPrice }</td>
                <td align="center">${p.salePrice }</td>
                <td align="center">${p.lowSalePrice }</td>
                <td align="center">${p.cdKey }</td>
                <td align="center">
                    <c:if test="${p.saleStatus==1}">已售</c:if>
                    <c:if test="${p.saleStatus==0}">未售</c:if>
                </td>
                <td align="center">${p.supplier }</td>
                <td align="center">${p.remark }</td>
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
        <a href="<%=basePath%>productservlet?opr=first&curPage=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>productservlet?opr=prior&curPage=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>productservlet?opr=next&curPage=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>productservlet?opr=last&curPage=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>productservlet" method = "post" name="form1">
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
