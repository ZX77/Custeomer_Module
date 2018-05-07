
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改客户资料</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script>
        function back() {
            location.href="${pageContext.request.contextPath }/customerinfoservlet";
        }
    </script>
</head>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>customerinfoservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <input type="hidden" name="custId" value="${customerInfo.custId}">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改客户资料</legend>
    </fieldset>

    <center>
        <div class="layui-form-item" style="margin-left: 330px">
            <div class="layui-form-label">
                客户名称:
            </div>
            <div class="layui-input-inline">
                <input name="custname"  value="${customerInfo.custname}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入客户名称">
            </div>

            <div class="layui-form-label">
                行业:
            </div>
            <div class="layui-input-inline">
                <input name="custtype" value="${customerInfo.custtype}" type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入行业" >
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">银行账号</label>
                <div class="layui-input-inline">
                    <input type="text" value="${customerInfo.bankAccount}" name="bankAccount" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入银行账号">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开户银行</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankName" value="${customerInfo.bankName}" lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入开户银行">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人</label>
                <div class="layui-input-inline">
                    <input type="text" name="Contact" value="${customerInfo.contact}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入联系人">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="Phone" value="${customerInfo.phone}" lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入电话">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开票日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="TicketName" value="${customerInfo.ticketName}" id="TicketName" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开票地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="TicketAddr" value="${customerInfo.ticketAddr}" lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入开票地址">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开票电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="TicketTel" value="${customerInfo.ticketTel}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入开票电话">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">纳税登记号</label>
                <div class="layui-input-inline">
                    <input type="text" name="TaxNo" value="${customerInfo.taxNo}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入纳税登记号">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户状态</label>
                <div class="layui-input-inline">
                    <select name="custState">
                        <c:if test="${customerInfo.custState=='成交客户'}">
                            <option value=成交客户 selected>成交客户</option>
                            <option value="新客户">新客户</option>
                        </c:if>
                        <c:if test="${customerInfo.custState=='新客户'}">
                            <option value="成交客户">成交客户</option>
                            <option value="新客户" selected>新客户</option>
                        </c:if>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">业务员</label>
                <div class="layui-input-inline">
                    <select name = "username">
                        <c:forEach items="${usersList}" var="d">
                            <c:if test="${customerInfo.username==d.userid}">
                                <option value="${d.userid}" selected>${d.username}</option>
                            </c:if>
                            <c:if test="${customerInfo.username!=d.userid}">
                                <option value="${d.userid}">${d.username}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                信息来源:
            </label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" placeholder = "请说明信息来源" name = "source">${customerInfo.source}</textarea>
            </div>
        </div>
        <input type="submit" value=" 保 存 " class="layui-btn">
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
            elem: '#TicketName'
        });
    });
</script>
</body>
</html>
