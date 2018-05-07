
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增供应商信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <script src = "jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function check() {
            if(form1.totalMoney.value==""){
                alert("请输入订单金额");
                form1.totalMoney.focus();
                return false;
            }
            if(form1.oprtime.value==""){
                alert("请输入开票时间");
                form1.oprtime.focus();
                return false;
            }
            if(form1.operator.value==""){
                alert("请输入开票人");
                form1.operator.focus();
                return false;
            }
            if(form1.process.value==""){
                alert("请输入进度");
                form1.process.focus();
                return false;
            }
            return true;
        }

        function init(){
            //获取系统当前时间
            var d = new Date();
            var year = d.getYear()+1900;
            var month = d.getMonth()+1;
            var day = d.getDate();
            var sdate = year +"-"+month+"-"+day;
            $("#oprtime").val(sdate);
            $("#remark").val("");
            $("#prodCount").val("1");
            $("#saleMoney").val("0");
            $("#regPerson").val("");
            $("#regPassword").val("");
            //更改下拉框默认选中第一项
            $("#userid option:first").prop("selected", 'selected');
            $("#status option:first").prop("selected", 'selected');
            $("#prodid option:first").prop("selected", 'selected');
            $("#unitId option:first").prop("selected", 'selected');

            var url="${pageContext.request.contextPath }/ordersservlet";
            $.post(
                url,
                {
                    "action":'orderId',
                    "prodid":$("#prodid").val()
                },function(data){
                    $("#saleMoney").val(data.price);
                    $("#orderId").val(data.orderId);
                    $("#unitId").val(data.unitId);
                },"json"
            );
        }
        function add() {
            var custid = $("#custid").val();
            var  orderId = $("#orderId").val();
            var  oprtime = $("#oprtime").val()
            var  remark = $("#remark").val();
            var  prodCount = $("#prodCount").val();
            var  saleMoney = $("#saleMoney").val();
            var  regPerson = $("#regPerson").val();
            var  regPassword = $("#regPassword").val();
            var  userid = $("#userid").val();
            var  status = $("#status").val();
            var  prodid = $("#prodid").val();
            var  unitId = $("#unitId").val();
            if(orderId==""){
                alert("请点击新增订单按钮，新增一笔订单");
                return false;
            }
            if(oprtime==""){
                alert("请选择下单时间");
                return false;
            }

            if(prodCount==""){
                alert("请输入商品数量");
                $("#prodCount").val("1")
                form1.prodCount.focus();
                return false;
            }
            if(isNaN(prodCount)){
                alert("商品数量含有非法字符");
                return false;
            }
            if(saleMoney==""){
                alert("请输入商品单价");
                $("#saleMoney").val("0")
                form1.saleMoney.focus();
                return false;
            }
            if(isNaN(saleMoney)){
                alert("销售单价含有非法字符");
                $("#saleMoney").val(0);
                return false;
            }
            var url="${pageContext.request.contextPath }/ordersservlet";
            $.post(
                url,
                {
                    "custid":custid,
                    "action":'add',
                    "orderId":orderId,
                    "oprtime":oprtime,
                    "remark":remark,
                    "prodid":prodid,
                    "unitId":unitId,
                    "prodCount":prodCount,
                    "saleMoney":saleMoney,
                    "regPerson":regPerson,
                    "regPassword":regPassword,
                    "status":status,
                    "userid":userid
                },function(data){

                },"json"
            );
            return true;
        }
    </script>
</head>
<body>
<h2 align="新增订单表"></h2>
<img src="image/back.jpg" style="width: 35px;height: 25px;">
    <center>
        <button class="layui-btn" onclick="init()">添加订单详情</button>
    </center>
<form action="<%=basePath%>ordersservlet?action=add" class="layui-form" method="post" id = "form1" name="form1" onsubmit="return check();">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>新增订单资料</legend>
    </fieldset>
    <center>
    <div class="layui-form-item">
        <div class="layui-form-label">
            客户名称:
        </div>
        <div class="layui-input-inline">
            <select name="custid" id = "custid" >
                <c:forEach items="${customerInfoList}" var="s">
                    <option value="${s.custId}">${s.custname}</option>
                </c:forEach>
            </select>
        </div>
        <div class="layui-form-label">
            业务员:
        </div>
        <div class="layui-input-inline">
            <select name="userid" id = "userid" >
                <c:forEach items="${usersList}" var="s">
                    <option value="${s.userid}">${s.username}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item" >
        <div class="layui-form-label">
            开票时间:
        </div>
        <div class="layui-input-inline">
            <input type="text" name="oprtime" id="oprtime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">
                描述:
            </label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" placeholder = "请说明原因" name = "remark" id = "remark"></textarea>
            </div>
        </div>
    </center>
        <fieldset class="layui-elem-field layui-field-title">
            <legend>新增订单明细</legend>
        </fieldset>
            <div class="layui-form-item">
                <div class="layui-form-label">订单编号</div>
                <div class="layui-input-inline">
                    <input type="text" id = "orderId" name="orderId"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入订单编号">
                </div>

                <div class="layui-form-label">商品名称</div>
                <div class="layui-input-inline">
                    <select name = "prodid" id = "prodid" lay-filter="level" >
                        <c:forEach items="${productList}" var="d">
                            <option value="${d.prodid}">${d.prodname}</option>
                        </c:forEach>
                    </select>
                </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">销售数量</label>
                        <div class="layui-input-inline">
                            <input type="text" id = "prodCount" name="prodCount"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入销售数量">
                        </div>
                    </div>
                </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">销售金额</label>
                    <div class="layui-input-inline">
                        <input name="saleMoney" id="saleMoney"  type="text" class="layui-input" autocomplete="off" lay-verify="required" placeholder="请输入销售金额" >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">单位</label>
                    <div class="layui-input-inline">
                        <select name="unitId" id="unitId">
                            <c:forEach items="${unitList}" var = "d">
                                <option value="${d.unitId}">${d.unitName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">注册联系人</label>
                        <div class="layui-input-inline">
                            <input type="text" name="regPerson"  id="regPerson"  lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入注册联系人">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">注册密码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="regPassword" id="regPassword" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入注册密码">
                        </div>
                    </div>
                </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    描述
                </label>
                <div class="layui-input-block">
                    <textarea class="layui-textarea" placeholder = "请说明描述"  name = "remark"></textarea>
                </div>
            </div>
    <center>
        <input type="buttn" onclick = "add();" value=" 保 存 " class="layui-btn">
    </center>
</form>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script src="layui/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate','table'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate
            ,table = layui.table //表格
            ,$ = layui.$

        //日期
        laydate.render({
            elem: '#oprtime'
        });
        form.on('select(level)', function(){
            var value = $("#prodid").val();
            var url = "${pageContext.request.contextPath }/ordersservlet"
            $.post(
                url,
                {
                    "action":'price',
                    "prodid":value
                },function(data){
                    $("#saleMoney").val(data.price);
                    $("#unitId").val(data.unitId);
                },"json"
            );
        });
    });
</script>
</body>
</html>
