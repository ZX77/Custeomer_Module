<%@ page import="bean.PageObject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>订单信息</title>
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
        #select{
            width: 150px;
            height: 35px;
            margin-left: 20px;
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
                    window.location.href="<%=basePath%>ordersservlet?action=del&orderId="+this.value;
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
                    window.location.href="<%=basePath%>ordersservlet?action=update&orderId="+this.value;
                });
            });
            $("#btn").click(function () {
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
                    var value = this.value;
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.open({
                            type: 2,
                            offset: '20px',
                            title:'客户基本资料',
                            area:['1000px', '500px'],
                            content: '<%=basePath%>orderdetailservlet?orderId='+value
                        });
                    });
                });
            });
        });
        //状态更改
        function status(statu) {
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
                window.location.href="<%=basePath%>ordersservlet?action=status&orderId="+this.value+"&statu="+statu;
            });
        }
    </script>
</head>
<body>
<form action="<%=basePath%>ordersservlet?param=select" class="layui-form" method="post" name="form1" style="text-align: center">
    <br/><br/>
    <div class="layui-form-item">
        <div class="layui-inline" style="margin-left: -20px">
            <label class="layui-form-label">选择客户:</label>
            <div class="layui-input-inline">
                <select name = "selected" id = "select">
                    <option value="0" selected>直接选择/搜索选择</option>
                    <c:forEach items="${customerInfoList}" var = "s">
                        <c:if test="${custid==s.custId}">
                            <option value="${s.custId}" selected>${s.custname}</option>
                        </c:if>
                        <c:if test="${custid!=s.custId}">
                            <option value="${s.custId}">${s.custname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <input type="submit" value="查询" class="layui-btn" style="margin-left: 20px;margin-top: -5px">
    </div>
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>订单表信息</legend>
</fieldset>
<a href="<%=basePath%>ordersservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "btn">查看详情</button>
<a href="<%=basePath%>ordersservlet?action=GoProd"><button class="layui-btn layui-btn-small">采购入库</button></a>
<button class="layui-btn layui-btn-small" id = "btn1" onclick = "status('已出货')">已出货</button>
<button class="layui-btn layui-btn-small" id = "btn2" onclick = "status('实施中')">实施中</button>
<button class="layui-btn layui-btn-small" id = "btn3" onclick = "status('已收款')">已收款</button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align = "center">编号</td>
            <td align="center">订单编号</td>
            <td align="center">客户名称</td>
            <td align="center">业务员</td>
            <td align="center">订单类别</td>
            <td align="center">订单状态</td>
            <td align="center">进度</td>
            <td align="center">订单金额</td>
            <td align="center">开票时间</td>
            <td align="center">开票人</td>
            <td align="center">描述</td>
        </tr>
        </thead>
        <tobody>
            <c:forEach items="${ordersList}" var = "s">
                <tr>
                    <td align="center"><input name="subBox" type="checkbox" lay-skin="primary" value="${s.orderId}" /></td>
                    <td align="center">${s.orderId}</td>
                    <td align="center">${s.customername}</td>
                    <td align="center">${s.username}</td>
                    <td align="center">${s.orderType}</td>
                    <td align="center">${s.orderStatus}</td>
                    <td align="center">${s.process}</td>
                    <td align="center">${s.totalMoney}</td>
                    <td align="center">${s.oprtime}</td>
                    <td align="center">${s.operator}</td>
                    <td align="center">${s.remark}</td>
                </tr>
            </c:forEach>
        </tobody>
    </table>
    <div id = "list">
        <%
            PageObject pageObject = (PageObject)request.getAttribute("pager");
        %>
        <div class="layui-form-label">
            当前页：<%=pageObject.getCurPage() %>/<%=pageObject.getPageCount() %>
        </div>
        <a href="<%=basePath%>ordersservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>ordersservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>ordersservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>ordersservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>ordersservlet" method = "post" name="form1" onsubmit="return check();">
                <input type="text" id = "text" name = "val">
                <input type="submit" class="layui-btn layui-btn-small" value="GO">
            </form>
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="page/user/allUsers.js"></script>
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
