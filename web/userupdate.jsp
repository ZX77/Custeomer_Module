<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户新增界面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>
<script src="jquery/jquery-3.3.1.js"></script>
<style type="text/css">
    #submit{
        margin-left: 350px;
    }
    #submit input{
        color: white;
    }
</style>
<script type="text/javascript">
    function check() {
        //控制
        if(isNaN(form1.degreeSalary.value)){
            alert("岗位工资含有非法字符");
            form1.degreeSalary.focus();
            form1.degreeSalary.value="0";
            return false;
        }
        if($("#joinDate")==""){
            alert("请输入入职日期");
            form1.joinDate.focus();
            return false;
        }
        if($("#pasaword")==""){
            alert("请输入用户密码");
            form1.pasaword.focus();
            return false;
        }
        if(form1.username.value==""){
            alert("请输入用户名称");
            form1.username.focus();
            return false;
        }
        if(form1.cardno.value==""){
            alert("请输入身份证号码");
            form1.cardno.focus();
            return false;
        }
        return true;
    }
    function back() {
        location.href="${pageContext.request.contextPath }/usersservlet";
    }
</script>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back()">
<form action="<%=basePath%>usersservlet?action=updateSave" class="layui-form" method = "post" name = "form1" onsubmit="return check()">
    <input type="hidden" name="userid" value="${user.userid}">
    <div class="layui-form-item">
        <div class="layui-form-label">
            用户名:
        </div>
            <div class="layui-input-inline">
                <input name = "username" id = "user1" type="text" class="layui-input" readonly="readonly" autocomplete="off" lay-verify="required" value="${user.username}">
            </div>
        <div class="layui-form-mid layui-word-aux" id = "usertip">
            请务必填写用户名
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline">
                <input type="tel" name="mobile" id = "mobile" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入手机" value="${user.mobile}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" id="email"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入邮箱" value="${user.email}">
            </div>
        </div>

        <label class="layui-form-label">
            部门:
        </label>
        <div class="layui-input-inline">
            <select name = "depid">
                <c:forEach items="${depList}" var="d">
                    <c:if test="${user.depid==d.depid}">
                        <option value="${d.depid}" selected>${d.depname}</option>
                    </c:if>
                    <c:if test="${user.depid!=d.depid}">
                        <option value="${d.depid}">${d.depname}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-form-label">
                QQ:
            </div>
            <div class="layui-input-inline">
                <input name = "qqcode" id = "qqcode" type="text" class="layui-input" placeholder="请输入QQ" autocomplete="off" lay-verify="required" value="${user.qqcode}">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-form-label">
                微信:
            </div>
            <div class="layui-input-inline">
                <input name = "weixin" id = "weixin" type="text" class="layui-input" placeholder="请输入微信" autocomplete="off" lay-verify="required" value="${user.weixin}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">
                管理员类别:
            </label>
            <div class="layui-input-inline">
                <select name="managerType">
                    <c:if test="${user.managerType==0}">
                        <option value="0" selected>系统管理员</option>
                        <option value="1">部门经理</option>
                        <option value="2">财务</option>
                        <option value="3">职员</option>
                    </c:if>
                    <c:if test="${user.managerType==1}">
                        <option value="0">系统管理员</option>
                        <option value="1" selected>部门经理</option>
                        <option value="2">财务</option>
                        <option value="3">职员</option>
                    </c:if>
                    <c:if test="${user.managerType==2}">
                        <option value="0">系统管理员</option>
                        <option value="1">部门经理</option>
                        <option value="2" selected>财务</option>
                        <option value="3">职员</option>
                    </c:if>
                    <c:if test="${user.managerType==3}">
                        <option value="0">系统管理员</option>
                        <option value="1">部门经理</option>
                        <option value="2">财务</option>
                        <option value="3" selected>职员</option>
                    </c:if>
                    <c:if test="${user==null}">
                        <option value="0">系统管理员</option>
                        <option value="1">部门经理</option>
                        <option value="2">财务</option>
                        <option value="3">职员</option>
                    </c:if>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">
                岗位类别:
            </label>
            <div class="layui-input-inline">
                <select name="degreeid" >
                    <c:forEach items="${degreesList}" var="f">
                        <c:if test="${user.depid==f.degreeid}">
                            <option value="${f.degreeid}" selected>${f.degreename}</option>
                        </c:if>
                        <c:if test="${user.depid!=f.degreeid}">
                            <option value="${f.degreeid}">${f.degreename}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-form-label">
                职位:
            </div>
            <div class="layui-input-inline">
                <input name = "jobname" id = "jobname" type="text" class="layui-input" placeholder="请输入微信" autocomplete="off" lay-verify="required" value="${user.jobname}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-form-label">
                身份证:
            </div>
            <div class="layui-input-inline">
                <input name = "cardno" id = "cardno"  type="text" class="layui-input" placeholder="请输入身份证" autocomplete="off" lay-verify="required" value="${user.cardno}">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-form-label">
                开户银行:
            </div>
            <div class="layui-input-inline">
                <input name = "bankName" id = "bankName" type="text" class="layui-input" placeholder="请输入开户银行" autocomplete="off" lay-verify="required" value="${user.bankName}">
            </div>
        </div>

        <div class="layui-inline">
            <div class="layui-form-label">
                银行账号:
            </div>
            <div class="layui-input-inline">
                <input name = "bankCardNo" id = "bankCardNo" type="text" class="layui-input" placeholder="请输入银行卡号" autocomplete="off" lay-verify="required" value="${user.bankCardNo}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">入职日期</label>
            <div class="layui-input-inline">
                <input type="text" name="joinDate" id="joinDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${user.joinDate}">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">离职日期</label>
            <div class="layui-input-inline">
                <input type="text" name="levelDate" id="levelDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${user.levelDate}">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">转正日期</label>
            <div class="layui-input-inline">
                <input type="text" name="workDate" id="workDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${user.workDate}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">
            基本工资:
        </label>
        <div class="layui-input-inline">
            <div class="layui-input-inline">
                <input name = "baseSalary" id = "baseSalary" type="text" class="layui-input" placeholder="请输入" autocomplete="off" lay-verify="required" value="${user.baseSalary}">
            </div>
        </div>

        <div class="layui-form-label">
            岗位工资:
        </div>
        <div class="layui-input-inline">
            <input name = "degreeSalary" id = "degreeSalary" type="text" class="layui-input" placeholder="请输入" autocomplete="off" lay-verify="required" value="${user.degreeSalary}">
        </div>

        <div class="layui-form-label">
            联系地址:
        </div>
        <div class="layui-input-inline">
            <input name = "addr" id = "addr" type="text" class="layui-input" placeholder="请输入" autocomplete="off" lay-verify="required" value="${user.addr}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">
            说明:
        </label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" placeholder = "请说明原因" id = "remark" name = "remark">${user.remark}</textarea>
        </div>
    </div>
    <div id = "submit">
        <input type="submit" class="layui-btn" value="提交信息">
    </div>
</form>
</body>
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
            elem: '#joinDate'
        });
        laydate.render({
            elem: '#levelDate'
        });
        laydate.render({
            elem: '#workDate'
        });

    });
</script>
</html>