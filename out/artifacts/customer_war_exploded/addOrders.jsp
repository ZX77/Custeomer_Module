<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>新增订单</title>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
    <style type="text/css">
        input{
            width: 150px;
            height: 30px;
            border: 1px #b2b2b2 solid;
        }
        select{
            border: 1px #b2b2b2 solid;
        }
        font{
            font-size: 18px;
            font-family: 微软雅黑;
            font-weight: 500;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $('#tt').datagrid({
                toolbar:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:function(){
                        add();
                    }
                }],
                onBeforeLoad:function(){

                },
                onClickRow:function(rowIndex){

                }
            });
        });
        function init(){
            //获取系统当前时间
            var d = new Date();
            var year = d.getYear()+1900;
            var month = d.getMonth()+1;
            var day = d.getDate();
            var sdate = year +"-"+month+"-"+day;
            $("#oprtime").datebox('setValue',sdate);
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

        //获取商品价格
        function prodPrice(){
            var url="${pageContext.request.contextPath }/ordersservlet";
            $.post(
                url,
                {
                    "action":'price',
                    "prodid":$("#prodid").val()
                },function(data){
                    $("#saleMoney").val(data.price);
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
                    $("#tt").datagrid("loadData",data.detailList);
                },"json"
            );
            return true;
        }
        function back(){
            location.href="${pageContext.request.contextPath }/ordersservlet";
        }
    </script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" BACKGROUND="easyui/images/maintitle.jpg" class="tablelist2">
    <tr  class="tableselect2">
        <td>
            <div class="tools">
                <ul class="toolbar">
                    <li class="click"><a href="javascript:void(0);" onclick="back();"><span><img src="image/back.jpg" style="width: 35px;height: 25px;"></span></a></li>
                    <li class="click"><a href="javascript:void(0);" onclick="init();"><span><img src="easyui/images/add.png"></span>新增订单</a></li>
                </ul>
            </div>
        </td>
    </tr>
</table>
<fieldset>
    <legend><font>订单信息</font></legend>
    <table border="0" height="80" style="margin-left: 60px">
        <tr>
            <td><font>订单编号：</font></td>
            <td><input type="text" name="orderId" id="orderId" style="width:150px" value="${order.orderId}" readonly="readonly"></td>
            <td><font>下单时间：</font></td>
            <td><input type="text" name="oprtime" id="oprtime" style="width:150px;height: 50px;" class="easyui-datebox" value="${order.oprtime}"></td>
            <td align="right"><font>业务人员：</font></td>
            <td>
                <select name="userid" id="userid" STYLE="height: 30px;width: 140px">
                    <c:forEach items="${usersList}" var="c">
                        <c:if test="${order.userid==c.userid}">
                            <option value="${c.userid}" selected>${c.username}</option>
                        </c:if>
                        <c:if test="${order.userid!=c.userid}">
                            <option value="${c.userid}">${c.username}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td><font>订单说明：</font></td>
            <td><input type="text" name="remark" id="remark" value="${order.remark}"></td>
            <td><font>客户:</font></td>
            <td>
                <select name="custid" id = "custid" style="width: 140px;height: 30px;">
                    <c:forEach items="${customerInfoList}" var="s">
                        <option value="${s.custId}">${s.custname}</option>
                    </c:forEach>
                </select>
            </td>
            <td></td>
            <td></td>
        </tr>
    </table>
</fieldset>
<fieldset>
    <legend><font>商品信息</font></legend>
    <table border="0" height="100" style="margin-left: 60px">
        <tr>
            <td align="right"><font>商品名称：</font></td>
            <td>
                <select name="prodid" id="prodid" style="width:140px;height: 30px" onchange="prodPrice();">
                    <c:forEach items="${productList}" var="d">
                        <option value="${d.prodid}">${d.prodname}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="right"><font>商品单位：</font></td>
            <td>
                <select name="unitId" id="unitId"  style="width:140px;height: 30px;">
                    <c:forEach items="${unitList}" var="d">
                        <option value="${d.unitId}">${d.unitName}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="right"><font>销售数量：</font></td>
            <td>
                <input type="text" name="prodCount" id="prodCount"/>
            </td>
            <td align="right"><font>销售单价：</font></td>
            <td>
                <input type="text" name="saleMoney" id="saleMoney"/>
            </td>
        </tr>
        <tr>
            <td align="right"><font>注册人：</font></td>
            <td>
                <input type="text" name="regPerson" id="regPerson"/>
            </td>
            <td align="right"><font>销售类型：</font></td>
            <td>
                <select name="status" id="status"  style="width:140px;height: 30px;">
                    <option value="销售" selected>销售</option>
                    <option value="赠送">赠送</option>
                    <option value="配套">配套</option>
                </select>
            </td>
            <td align="right"><font>注册密码：</font></td>
            <td colspan="3">
                <input type="text" name="regPassword" id="regPassword"/>
            </td>
        </tr>
    </table>
</fieldset>
    <table id="tt" style="width:auto;height:auto"
           data-options="iconCls:'icon-edit',singleSelect:true">
        <thead>
        <tr>
            <th data-options="field:'orderId',width:100,align:'center'">订单编号</th>
            <th data-options="field:'prodname',width:220,align:'center'">商品名称</th>
            <th data-options="field:'UnitId',width:80,align:'center'">单位</th>
            <th data-options="field:'prodCount',width:80,align:'center'">数量</th>
            <th data-options="field:'saleMoney',width:80,align:'center'">单价</th>
            <th data-options="field:'totalMoney',width:80,align:'center'">小计</th>
            <th data-options="field:'status',width:100,align:'center'">销售类别</th>
            <th data-options="field:'regPerson',width:120,align:'center'">注册联系人</th>
            <th data-options="field:'regPassword',width:200,align:'center'">注册密码</th>
        </tr>
        </thead>
    </table>
</body>
</html>
