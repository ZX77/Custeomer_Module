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
    <title>用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <script src = "jquery/jquery-3.3.1.js"></script>
    <style type="text/css">
        #list{
            margin-top: 70px;
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
                        var url = "<%=basePath%>usersservlet";
                        window.location.href="<%=basePath%>usersservlet?action=del&userid="+this.value;
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
                        window.location.href="<%=basePath%>usersservlet?action=update&userid="+this.value;

                    });
                });
        });

        function check() {
            var val = form1.val.value;
            if(isNaN(val)){
                alert("请输入合法的字符");
                return false;
            }
            return true;
        }

        //查询员工搜索
        function reload() {
            var username = $("#demoReload").val();
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.open({
                    type: 2,
                    title:'员工业绩资料',
                    area:['1200px', '500px'],
                    content: '<%=basePath%>userinfor?username='+username
                });
            });
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户信息</legend>
</fieldset>
<div class="demoTable" style="text-align: center">
    搜索员工业绩信息：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload" onclick="reload();">搜索</button>
</div>
<a href="<%=basePath%>usersservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td><input id = "checkAll" class="checkAll" type="checkbox" lay-skin="primary" /></td>
            <td align="center">用户名</td>
            <td align="center">部门</td>
            <td align="center">手机号</td>
            <td align="center">职位</td>
            <td align="center">管理员类别</td>
            <td align="center">身份证</td>
            <td align="center">基本工资</td>
            <td align="center">联系地址</td>
            <td align="center">状态</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var = "d">
        <tr>
            <td><input name="subBox" id="lists" type="checkbox" lay-skin="primary" value="${d.userid}" /></td>
            <td align="center">${d.username }</td>
            <td align="center">${d.depname }</td>
            <td align="center">${d.mobile }</td>
            <td align="center">${d.jobname }</td>
            <td align="center">
                <c:if test="${d.managerType==0}">系统管理员</c:if>
                <c:if test="${d.managerType==1}">部门经理</c:if>
                <c:if test="${d.managerType==2}">财务</c:if>
                <c:if test="${d.managerType==3}">职员</c:if>
            </td>
            <td align="center">${d.cardno }</td>
            <td align="center">${d.baseSalary }</td>
            <td align="center">${d.addr }</td>
            <td align="center">
                <a href="<%=basePath%>usersservlet?action=status&userid=${d.userid}&status=${d.status}"><button class="layui-btn layui-btn-small"><c:if test="${d.status==1}">启用</c:if>
                    <c:if test="${d.status==0}">禁用</c:if></button></a>
            </td>
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
        <a href="<%=basePath%>usersservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>usersservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>usersservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>usersservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>usersservlet" method = "post" name="form1" onsubmit="return check();">
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
