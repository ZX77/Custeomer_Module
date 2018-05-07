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
    <title>合同信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="../../css/font_eolqem241z66flxr.css" media="all" />
    <link rel="stylesheet" href="css/main.css" media="all" />
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
            position: absolute;
        }
        #select{
            width: 170px;
            height: 30px;
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
                    var url = "<%=basePath%>contractservlet";
                    window.location.href="<%=basePath%>contractservlet?action=del&contract_id="+this.value;
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
                    window.location.href="<%=basePath%>contractservlet?action=update&contract_id="+this.value;

                });
            });
            $("#attactbtn").click(function () {
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
                    window.location.href="<%=basePath%>contractattachservlet?contract_id="+this.value;

                });
            });
        });
    </script>
</head>
<body>

<form action="<%=basePath%>contractservlet?param=select" class="layui-form" method="post" name="form1" style="text-align: center">
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
    <legend>合同表信息</legend>
</fieldset>
    <a href="contractservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
    <button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
    <button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
    <button class="layui-btn layui-btn-small" id = "attactbtn">合同附件</button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align = "center">合同编号</td>
            <td align="center">客户名称</td>
            <td align="center">合同签订时间</td>
            <td align="center">合同到期时间</td>
            <td align="center">合同金额</td>
            <td align="center">支付类别</td>
            <td align="center">合同状态</td>
            <td align="center">业务员</td>
            <td align="center">操作员</td>
            <td align="center">操作时间</td>
        </tr>
        </thead>
        <tobody>
            <c:forEach items="${contractsList}" var = "s">
                <tr>
                    <td align = "center"><input name="subBox" type="checkbox" lay-skin="primary" value="${s.contract_id}" /></td>
                    <td align="center">${s.customer}</td>
                    <td align="center">${s.contract_time}</td>
                    <td align="center">${s.due_time}</td>
                    <td align="center">${s.total_money}</td>
                    <td align="center">${s.pay_type}</td>
                    <td align="center">${s.status}</td>
                    <td align="center">${s.username}</td>
                    <td align="center">${s.operator}</td>
                    <td align="center">${s.oprtime}</td>
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
        <a href="<%=basePath%>contractservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>contractservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>contractservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>contractservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>contractservlet" method = "post" name="form1" onsubmit="return check();">
                <input type="text" id = "text" name = "val">
                <input type="submit" class="layui-btn layui-btn-small" value="GO">
            </form>
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="page/user/allUsers.js"></script>
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
</html>
