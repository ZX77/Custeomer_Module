<%@ page import="bean.contractAttach" %>
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
    <title>合同附件表</title>
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
                    window.location.href="<%=basePath%>contractattachservlet?action=del&contractAttach_id="+this.value;
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
                    window.location.href="<%=basePath%>contractattachservlet?action=update&contractAttach_id="+this.value;

                });
            });
        });
        function back() {
            location.href="${pageContext.request.contextPath }/contractservlet";
        }
    </script>
</head>
<body>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>合同附件表</legend>
</fieldset>
    <a href="contractattachservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
    <button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
    <button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
    <a href="contractattachservlet?action=img"><button class="layui-btn layui-btn-small" id = "btn" onclick="">附件预览</button></a>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align = "center">合同附件编号</td>
            <td align = "center">合同编号</td>
            <td align="center">排序号</td>
            <td align="center">附件名称</td>
            <td align="center">上传时间</td>
            <td align="center">操作员</td>
            <td align="center">附件地址</td>
        </tr>
        </thead>
        <tobody>
            <c:forEach items="${contractattachList}" var = "s">
                <tr>
                    <td align = "center"><input name="subBox" type="checkbox" lay-skin="primary" value="${s.contractAttach_id}" /></td>
                    <td align="center">${s.contract_id}</td>
                    <td align="center">${s.seq}</td>
                    <td align="center">${s.attachFile}</td>
                    <td align="center">${s.uploadTime}</td>
                    <td align="center">${s.user_name}</td>
                    <td align="center">${s.attachURL}</td>
                </tr>
            </c:forEach>
        </tobody>
    </table>
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
    });
    //监听表格复选框选择
    table.on('checkbox(demo)', function(obj){
        console.log(obj)
    });
</script>
</html>
