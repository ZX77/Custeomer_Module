<%--
  Created by IntelliJ IDEA.
  User: 林longqi
  Date: 2018/3/23
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.Dep" %>
<%@ page import="bean.PageObject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增商品资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
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
<h2 align="新增商品资料"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<form action="<%=basePath%>productservlet?action=add" class="layui-form" method="post" name="form1" onsubmit="return check();">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>新增商品资料</legend>
        </fieldset>
    <center>
    <div class="layui-form-item" style="margin-left: 260px">
        <div class="layui-form-label">
            商品编号:
        </div>
        <div class="layui-input-inline">
            <input name="prodid"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入商品编号">
        </div>

        <div class="layui-form-label" style="margin-left: 20px">
            商品名称:
        </div>
        <div class="layui-input-inline">
            <input name="prodname"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入商品名称" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-inline">
                <input type="text" name="prodModel" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入商品型号">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline">

            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品单位</label>
            <div class="layui-input-inline">
                <select name="prodUnit"  style="width:300px">
                    <c:forEach items="${unitList}" var="u">
                        <option value="${u.unitId}">${u.unitName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品规格</label>
            <div class="layui-input-inline">
                <input type="text" name="prodStyle"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入商品规格">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">库存数量</label>
            <div class="layui-input-inline">
                <input type="text" name="prodCount" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入库存数量">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">进货价格</label>
            <div class="layui-input-inline">
                <input type="text" name="inPrice"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入进货价格">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">销售价格</label>
            <div class="layui-input-inline">
                <input type="text" name="salePrice" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入销售价格">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">最低价格</label>
            <div class="layui-input-inline">
                <input type="text" name="lowSalePrice"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入最低价格">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-form-label">序列号</label>
        <div class="layui-input-inline">
            <input type="text" name="prodSerial" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入序列号">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">条码(CDkey)</label>
        <div class="layui-input-inline">
            <input type="text" name="cdKey"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入条码(CDkey)">
        </div>
    </div>
</div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">销售状态</label>
            <div class="layui-input-inline">
                <select name="saleStatus">
                    <option value=已售>已售</option>
                    <option value="未售">未售</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">供应商</label>
            <div class="layui-input-inline">
                <select name="supplierId"  style="width:300px">
                    <c:forEach items="${supplierList}" var="s">
                        <option value="${s.supplierId}">${s.supplierName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

        <div class="layui-form-item" style="margin-left: 120px;">
            <label class="layui-form-label">
                描述:
            </label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" placeholder = "请说明原因" id = "remark" name = "remark"></textarea>
            </div>
        </div>
    <input type="submit" class="layui-btn" value="保存">
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
