package servlet;

import bean.*;
import db.BaseDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class orderdetailservlet extends HttpServlet {
    //数据库操作类
    BaseDAO basedao = new BaseDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageObject pager = new PageObject();
        //读取页面的参数
        String opr = request.getParameter("opr");
        String curPage = request.getParameter("pagecur");
        String orderid = request.getParameter("orderId");
        if(curPage==null){
            curPage="1";
        }
        if(opr==null){
            opr = "first";
        }

        //第一步：计算总记录数
        int cnt = basedao.findCount11();
        //自动计算总页数
        pager.setRowCount(cnt);
        //设置当前页
        pager.setCurPage(Integer.parseInt(curPage));

        //设置当前页
        pager.setCurPage(Integer.parseInt(curPage));
        //设置操作符
        pager.setOpr(opr);
        List<OrderDetail> OrderDetailList = basedao.findByPage11(1000,1,Integer.parseInt(orderid));
        //定义request变量，在jsp页面中使用
        request.setAttribute("OrderDetailList", OrderDetailList);
        List<CustomerInfo> customerInfoList = basedao.findcustomer7();
        request.setAttribute("customerInfoList",customerInfoList);
        List<Orders> ordersList = basedao.findorders10();
        request.setAttribute("ordersList",ordersList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/OrderDetailList.jsp").forward(request, response);//需改jsp页面

    }
}
