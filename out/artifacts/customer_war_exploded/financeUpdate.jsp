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
    <title>修改收款管理资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        function check() {
            if(form1.prodid.value==""){
                alert("请输入产品名称");
                form1.prodid.focus();
                return false;
            }
            if(form1.remainMoney.value==""){
                alert("请输入应收金额");
                form1.remainMoney.focus();
                return false;
            }
            if(form1.paidMoney.value==""){
                alert("请输入收款金额");
                form1.paidMoney.focus();
                return false;
            }
            if(form1.orderMoney.value==""){
                alert("请输入订单金额");
                form1.orderMoney.focus();
                return false;
            }
            if(form1.paidPerson.value==""){
                alert("请输入交款人");
                form1.paidPerson.focus();
                return false;
            }
            if(form1.inbank.value==""){
                alert("请输入入账银行");
                form1.inbank.focus();
                return false;
            }
            if(form1.bankAccount.value==""){
                alert("请输入入账账号");
                form1.bankAccount.focus();
                return false;
            }
            if(form1.outbank.value==""){
                alert("请输入出账银行");
                form1.outbank.focus();
                return false;
            }
            if(form1.warrant.value==""){
                alert("请输入相关凭证号");
                form1.warrant.focus();
                return false;
            }
            if(form1.paidTime.value==""){
                alert("请输入交款时间");
                form1.paidTime.focus();
                return false;
            }
            if(form1.paidinTime.value==""){
                alert("请输入到账时间");
                form1.paidinTime.focus();
                return false;
            }
            if(form1.oprtime.value==""){
                alert("请输入录入时间");
                form1.oprtime.focus();
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/financeservlet";
        }
    </script>
</head>
<body>
<h2 align="修改收款管理资料"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>financeservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>修改收款管理资料</legend>
    </fieldset>
    <center>
        <input type="hidden" name="financeId" value="${finance.financeId}">
        <div class="layui-form-item" style="margin-left: 240px">
            <div class="layui-form-label" style="margin-left: 20px">订单号</div>
            <div class="layui-input-inline">
                <select name="orderId"  style="width:300px">
                    <c:forEach items="${ordersList}" var="u">
                        <c:if test="${u.orderId==finance.orderId}">
                            <option value="${u.orderId}" selected>${u.orderId}</option>
                        </c:if>
                        <c:if test="${u.orderId!=finance.orderId}">
                            <option value="${u.orderId}">${u.orderId}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <label class="layui-form-label">产品名称</label>
            <div class="layui-input-inline">
                <input name="prodid"  type="text" value="${finance.prodid}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入产品名称" >
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">收款方式类别</label>
                <div class="layui-input-inline">
                    <select name="paidtypeid"  style="width:300px">
                        <c:forEach items="${paidTypeList}" var="u">
                            <c:if test="${u.paidtypeid==finance.paidtypeid}">
                                <option value="${u.paidtypeid}" selected>${u.paidtypename}</option>
                            </c:if>
                            <c:if test="${u.paidtypeid!=finance.paidtypeid}">
                                <option value="${u.paidtypeid}">${u.paidtypename}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">应收金额</label>
                <div class="layui-input-inline">
                    <input name="remainMoney"  type="text" value="${finance.remainMoney}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入应收金额" >
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">收款金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="paidMoney" value="${finance.paidMoney}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入收款金额">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">订单金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="orderMoney" value="${finance.orderMoney}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入订单金额">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">交款人</label>
                <div class="layui-input-inline">
                    <input type="text" name="paidPerson" value="${finance.paidPerson}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入交款人">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">入账银行</label>
                <div class="layui-input-inline">
                    <input type="text" name="inbank" value="${finance.inbank}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入入账银行">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">入账账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankAccount" value="${finance.bankAccount}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入入账账号">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">出账银行</label>
                <div class="layui-input-inline">
                    <input type="text" name="outbank" value="${finance.outbank}" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入出账银行">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">相关凭证号</label>
                <div class="layui-input-inline">
                    <input type="text" name="warrant" value="${finance.warrant}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入相关凭证号">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">交款时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="paidTime" id="paidTime" value="${finance.paidTime}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入交款时间">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">到账日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="paidinTime" id="paidinTime" value="${finance.paidinTime}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入到账日期">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">是否有效</label>
                <div class="layui-input-inline">
                    <select name="invalid">
                        <c:if test="${finance.invalid=='有效'}">
                            <option value="有效" selected>有效</option>
                            <option value="作废">作废</option>
                        </c:if>
                        <c:if test="${finance.invalid=='作废'}">
                            <option value="有效">有效</option>
                            <option value="作废" selected>作废</option>
                        </c:if>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">录入时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="oprtime" id = "oprtime" value="${finance.oprtime}"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入录入时间">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">操作类别</label>
                <div class="layui-input-inline">
                    <select name="oprType">
                        <c:if test="${finance.oprType=='收款'}">
                            <option value="收款" selected>收款</option>
                            <option value="付款">付款</option>
                        </c:if>
                        <c:if test="${finance.oprType=='付款'}">
                            <option value="收款">有效</option>
                            <option value="付款" selected>付款</option>
                        </c:if>
                    </select>
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
            elem: '#oprtime'
        });
        //日期
        laydate.render({
            elem: '#paidTime'
        });
        //日期
        laydate.render({
            elem: '#paidinTime'
        });
    });
</script>
</body>
</html>
