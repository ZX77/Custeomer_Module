<%@ page import="bean.Users" %><%--
  Created by IntelliJ IDEA.
  User: 曾鑫
  Date: 2018/3/23
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <%
        String username = (String)session.getAttribute("username");
        int id = (int)session.getAttribute("id");
    %>
    <script type="text/javascript">
        function check() {
            var oldpassword = form1.oldpassword.value;
            var newpassword = form1.newpassword.value;
            var newpassword1 = form1.newpassword1.value;
            if(oldpassword==""){
                alert("请输入旧密码");
                return false;
            }
            if(newpassword == ""){
                alert("请输入新密码");
                return false;
            }
            if(newpassword1 == ""){
                alert("请输入确认密码");
                return false;
            }
            if(newpassword!=newpassword1){
                alert("新密码与旧密码不一致");
                return false;
            }
            return true;
        }
    </script>
</head>
<body class="childrenBody">
<form class="layui-form changePwd" action = "<%=basePath%>usersservlet?action=Pwdchange" method = "post" name = "form1" onsubmit="return check();">
    <input type="hidden" value="${id}" name = "userid">
    <div style="margin:0 0 15px 110px;color:#f00;">请输入旧密码请输入，新密码必须两次输入一致才能提交</div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" value="${username}" disabled class="layui-input layui-disabled layui-input-inline">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">旧密码</label>
        <div class="layui-input-block">
            <input type="password" value="" name="oldpassword" id="oldpassword" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd layui-input-inline">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password"  id = "newpassword" name = "newpassword" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd layui-input-inline">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" value="" id = "newpassword1" name="newpassword1" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd layui-input-inline">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script src = "layui/layui/layui.js"></script>
<script>
    layui.use('layer', function(){
        var layer = layui.layer;
        //返回订单状态
        if("${msg}"!=null && "${msg}"!="" ){
            layer.msg("${msg}");
        }
    });
</script>
</html>
