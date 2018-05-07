<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.PageObject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>派工单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <style type="text/css">
        #list{
            margin-top: 140px;
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
            margin-left: 370px;
            margin-top: -30px;
        }
    </style>
    <script src = "jquery/jquery-3.3.1.js"></script>
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
                    var url = "<%=basePath%>jobrecordservlet";
                    window.location.href="<%=basePath%>jobrecordservlet?action=del&jobId="+this.value;
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
                    window.location.href="<%=basePath%>jobrecordservlet?action=update&jobId="+this.value;
                });
            });
        });
    </script>
</head>
<body>
<form action="<%=basePath%>jobrecordservlet?select=select" class="layui-form" method="post" name="form1">
    <br/><br/>
    <div class="layui-form-item">
        <div class="layui-inline" style="margin-left: -20px">
            <label class="layui-form-label">选择客户:</label>
            <div class="layui-input-inline">
                <select name="custid" lay-search>
                    <c:if test="${jobRecord==null}">
                            <option value="0">---选择客户---</option>
                        <c:forEach items="${custList}" var="s">
                            <option value="${s.custId}">${s.custname}</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${jobRecord!=null}">
                        <option value="0">---选择客户---</option>
                        <c:forEach items="${custList}" var="s">
                            <c:if test="${jobRecord.custid==s.custId}">
                                <option value="${s.custId}" selected>${s.custname}</option>
                            </c:if>
                            <c:if test="${jobRecord.custid!=s.custId}">
                                <option value="${s.custId}">${s.custname}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="layui-inline" style="margin-left: -40px">
            <label class="layui-form-label">选择员工:</label>
            <div class="layui-input-inline" lay-search>
                <select name="userid" lay-search>
                    <c:if test="${jobRecord==null}">
                        <option value="0">---选择员工---</option>
                        <c:forEach items="${usersList}" var="s">
                            <option value="${s.userid}">${s.username}</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${jobRecord!=null}">
                        <option value="0">---选择员工---</option>
                        <c:forEach items="${usersList}" var="s">
                            <c:if test="${jobRecord.userid==s.userid}">
                                <option value="${s.userid}" selected>${s.username}</option>
                            </c:if>
                            <c:if test="${jobRecord.userid!=s.userid}">
                                <option value="${s.userid}">${s.username}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <input type="submit" value="查询" class="layui-btn" style="margin-left: 20px;margin-top: -5px">
    </div>
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>派工单</legend>
</fieldset>
<a href="<%=basePath%>jobrecordservlet?action=init"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
<button style="margin-left: 10px" class="layui-btn layui-btn-small" id = "updatebutt"><i class="layui-icon"></i></button>
<button class="layui-btn layui-btn-small" id = "butt"><i class="layui-icon"></i></button>
<div class="layui-form">
    <table class="layui-table ">
        <thead>
        <tr>
            <td align="center">派工编号</td>
            <td align="center">商品信息</td>
            <td align="center">客户名称</td>
            <td align="center">派工内容</td>
            <td align="center">派工完成情况</td>
            <td align="center">员工信息</td>
            <td align="center">客户评价</td>
            <td align="center">客户签名</td>
            <td align="center">开工时间</td>
            <td align="center">结束时间</td>
            <td align="center">人工天数</td>
            <td align="center">交通费</td>
            <td align="center">补助费</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jobRecordList}" var = "p">
            <tr>
                <td align="center"><input name="subBox" type="checkbox" lay-skin="primary" value="${p.jobId }" /></td>
                <td align="center">${p.prodinfor}</td>
                <td align="center">${p.custname }</td>
                <td align="center">${p.jobContent }</td>
                <td align="center">${p.callback }</td>
                <td align="center">${p.username }</td>
                <td align="center">${p.custEval }</td>
                <td align="center">
                    <c:if test="${p.custSign==1}">已签</c:if>
                    <c:if test="${p.custSign==0}">未签</c:if>
                </td>
                <td align="center">${p.startTime }</td>
                <td align="center">${p.endTime }</td>
                <td align="center">${p.workDay }</td>
                <td align="center">${p.busMoney }</td>
                <td align="center">${p.attachMoney }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id = "list">
        <%
            PageObject pageObject = (PageObject)request.getAttribute("pager");
        %>
        <div class="layui-form-label">
            当前页：<%=pageObject.getCurPage() %>/<%=pageObject.getPageCount() %>
        </div>
        <a href="<%=basePath%>jobrecordservlet?opr=first&pagecur=1"><button class="layui-btn layui-btn-small">首页</button></a>
        <a href="<%=basePath%>jobrecordservlet?opr=prior&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">上一页</button></a>
        <a href="<%=basePath%>jobrecordservlet?opr=next&pagecur=<%=pageObject.getCurPage()%>"><button class="layui-btn layui-btn-small">下一页</button></a>
        <a href="<%=basePath%>jobrecordservlet?opr=last&pagecur=<%=pageObject.getPageCount()%>"><button class="layui-btn layui-btn-small">末页</button></a>
        <div id = "form">
            <form action = "<%=basePath%>jobrecordservlet" method = "post" name="form1">
                <input type="text" id = "text" name = "val">
                <input type="submit" class="layui-btn layui-btn-small" value="GO">
            </form>
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="page/user/allUsers.js"></script>
<script src="<%=basePath%>layui/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#startTime'
        });
        //日期
        laydate.render({
            elem: '#endTime'
        });
    });
</script>
</html>
