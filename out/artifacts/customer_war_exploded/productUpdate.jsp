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
<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<html>
<head>
    <title>修改商品资料</title>
    <script type="text/javascript">
        function check() {
            if(form1.prodname.value==""){
                alert("请输入商品名称");
                form1.prodname.focus();
                return false;
            }
            if(form1.prodModel.value==""){
                alert("请输入商品型号");
                form1.prodModel.focus();
                return false;
            }
            if(form1.prodUnit.value==""){
                alert("请输入商品单位");
                form1.prodUnit.focus();
                return false;
            }
            if(form1.prodStyle.value==""){
                alert("请输入商品规格");
                form1.prodStyle.focus();
                return false;
            }
            if(form1.inPrice.value==""){
                alert("请输入商品进价");
                form1.inPrice.focus();
                return false;
            }
            if(form1.salePrice.value==""){
                alert("请输入商品售价");
                form1.salePrice.focus();
                return false;
            }
            if(form1.lowSalePrice.value==""){
                alert("请输入商品最低价");
                form1.lowSalePrice.focus();
                return false;
            }
            if(form1.prodSerial.value==""){
                alert("请输入商品序列号");
                form1.prodSerial.focus();
                return false;
            }
            if(form1.saleStatus.value==""){
                alert("请输入商品销售状态");
                form1.saleStatus.focus();
                return false;
            }
            return true;
        }
        function back() {
            location.href="${pageContext.request.contextPath }/productservlet";
        }
    </script>
</head>
<body>
<h2 align="修改商品资料"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="${pageContext.request.contextPath}/productservlet?action=updateSave" class="layui-form" method="post" name="form1" onsubmit="return check();">
    <center>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>新增商品信息</legend>
    </fieldset>
    <input type="hidden" name="prodid" value="${product.prodid}">
    <div class="layui-form-item" style="margin-left: 120px;">
        <div class="layui-form-label">
            商品名称:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="prodname" value="${product.prodname}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>

        <div class="layui-form-label">
            供应商:
        </div>
        <div class="layui-input-inline">
            <select name="supplierId" >
                <c:forEach items="${supplierList}" var="s">
                    <option value="${s.supplierId}">${s.supplierName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    </div>

    <div class="layui-form-item" style="margin-left: 120px;">
        <div class="layui-form-label">
            商品型号:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="prodModel" value="${product.prodModel}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>

        <div class="layui-form-label">
            商品单位:
        </div>
        <div class="layui-input-inline">
            <select name="prodUnit">
                <c:forEach items="${unitList}" var="u">
                    <c:if test="${u.unitId==product.prodUnit}">
                        <option value="${u.unitId}" selected>${u.unitName}</option>
                    </c:if>
                    <c:if test="${u.unitId!=product.prodUnit}">
                        <option value="${u.unitId}">${u.unitName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 120px;">
        <div class="layui-form-label">
            商品规格:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="prodStyle" value="${product.prodStyle}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>

        <div class="layui-form-label">
            库存数量:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="prodCount" value="${product.prodCount}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 120px;">
        <div class="layui-form-label">
            进货价格:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="inPrice" value="${product.inPrice}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>

        <div class="layui-form-label">
            销售价格:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="salePrice" value="${product.salePrice}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 120px;">
        <div class="layui-form-label">
            最低价格:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="lowSalePrice" value="${product.lowSalePrice}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>

        <div class="layui-form-label">
            序列号:
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="prodSerial" value="${product.prodSerial}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 120px;">
        <div class="layui-form-label">
            条码(CDkey):
        </div>
        <div class="layui-input-inline">
            <input  type="text" name="cdKey" value="${product.cdKey}" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入部门名称">
        </div>

        <div class="layui-form-label">
            销售状态:
        </div>
        <div class="layui-input-inline">
            <select name="saleStatus"  style="width:300px">
                <c:if test="${product.saleStatus==1}">
                    <option value=已售 selected>已售</option>
                    <option value="未售">未售</option>
                </c:if>
                <c:if test="${product.saleStatus==0}">
                    <option value="已售">已售</option>
                    <option value="未售" selected>未售</option>
                </c:if>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">
            描述:
        </label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" placeholder = "请说明原因" id = "remark" name = "remark">${product.remark}</textarea>
        </div>
    </div>
    <input type="submit" value=" 保 存 " class="layui-btn"></td>
    </center>
</form>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
    });
</script>
</body>
</html>
