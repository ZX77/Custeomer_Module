
function delse(servlet) {
    var arrChk = $("input[name='subBox']:checked");
    $(arrChk).each(function () {
        var url = "<%=basePath%>usersservlet";
        window.location.href="<%=basePath%>"+servlet+"?action=del&userid="+this.value;
    });
    if (arrChk.length == 0) {
        alert("没有选中")
    }
}

//修改
function updatese(servlet) {
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
        alert("ok");
        window.location.href="localhost:8080/user/"+servlet+"?action=update&userid="+this.value;
        alert("localhost:8080/user/"+servlet+"?action=update&userid="+this.value);

    });

    //判断是否选中
    $(function() {
        //判断是否全选
        $("#checkAll").click(function () {
            var a = $("#checkAll").attr("checked");
            $("input[name='subBox']").each(function () {
                $(this).attr("checked", a);
            });
        });
        $(".subBox").click(function () {
            $("input[name='subBox']").each(function () {
                if ($("input[name='subBox']").not("input:checked")) {
                    $("#checkAll").attr("checked", false);
                }
            })
        })
    });
}
