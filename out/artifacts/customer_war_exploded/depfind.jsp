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
    <title>部门信息</title>
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
                    var url = "<%=basePath%>depservlet";
                    window.location.href="<%=basePath%>depservlet?action=del&depid="+this.value;
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
                    window.location.href="<%=basePath%>depservlet?action=update&depid="+this.value;

                });
            });
        });

    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>部门信息</legend>
</fieldset>
<a href="<%=basePath%>depservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table " pagination="true"  id="changesize"  singleSelect="false"  data-options="onClickRow:getRowData">
        <thead>
        <tr>
            <td align="center" style="width: 100px;">部门编号</td>
            <td align="center">部门名称</td>
            <td align="center">部门负责人</td>
            <td align="center">部门描述</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${depList}" var="d">
            <tr>
                <td align="center"><input name="subBox" type="checkbox" lay-skin="primary" value="${d.depid}" /></td>
                <td align="center">${d.depname}</td>
                <td align="center">${d.chairman}</td>
                <td align="center">${d.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id = "list">
    <%
        PageObject pageObject = (PageObject)request.getAttribute("pager");
    %>
    <div class="layui-form-label">
        当前页：<%=pageObject.getCurPage() %>/<%=pageObject.getPageCount() %>
    </div>

    <a href="<%=basePath%>depservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
    <a href="<%=basePath%>depservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
    <a href="<%=basePath%>depservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
    <a href="<%=basePath%>depservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
    <div id = "form">
        <form action = "<%=basePath%>depservlet" method = "post" name="form1" onsubmit="return check();">
            <input type="text" id = "text" name = "val">
            <input type="submit" class="layui-btn layui-btn-small" value="GO">
        </form>
    </div>
</div>
</body>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="page/user/allUsers.js"></script>
</html>
