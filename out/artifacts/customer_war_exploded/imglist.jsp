<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        img{
            width:200px;
            height: 300px;
        }
        body{ background-color: #C2BE9E; }
        @keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        @-moz-keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        @-ms-keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        @-webkit-keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        .box{
            width:200px;
            height:300px;
            position:relative;
            margin:auto;
            margin-top:110px;
            transform-style:preserve-3d;
            transform:rotateX(-10deg);
            animation: fn 10s;
            animation-iteration-count: infinite;
            animation-timing-function: linear;
            position: relative
        }
        .box:hover{
            animation-play-state: paused;
        }
        .box>div{
            width:200px;
            height:300px;
            position: absolute;
            left:0;
            top:0;
        }
    </style>
    <script>
        function back() {
            location.href="${pageContext.request.contextPath }/contractattachservlet";
        }
    </script>
</head>
<body>
<jsp:include page="IncludeJS.jsp"></jsp:include>
<img src="image/back.jpg" style="width: 35px;height: 25px;" onclick="back();">
<div class="container">
    <div class="box">
        <%
            int a = 0;
        %>
        <c:forEach items="${attachVos}" var="a">
            <div style="transform: rotateY(<%=a%>deg) translateZ(275px);">
                <a href="<%=basePath%>/filepath${a.attachURL}"><img src="<%=basePath%>/filepath${a.attachURL}" width="200" height="150"></a>
            </div>
            <%
                a = a + 40;
            %>
        </c:forEach>
    </div>
</div>
</body>
<script>
    $(function(){
        $(".container a").hover(function(){
            $(".container a").mousemove(function(e){
                $("#pic").css({
                    "top":(e.pageY+10)+"px",
                    "left":(e.pageX+20)+"px"
                }).fadeIn("fast");
                // $("#pic").fadeIn("fast");
            });
        },function(){
            $("#pic").remove();
        });
    });
</script>
</html>
