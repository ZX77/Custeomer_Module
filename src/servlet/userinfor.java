package servlet;

import bean.Orders;
import bean.Users;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 曾鑫 on 2018/4/10.
 */
@WebServlet(name = "userinfor")
public class userinfor extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Users users = baseDAO.finduserinfor(username);
        List<Orders> ordersList1 = baseDAO.userstatus("已出货",users.getUserid());
        List<Orders> ordersList2 = baseDAO.userstatus("实施中",users.getUserid());
        List<Orders> ordersList3 = baseDAO.userstatus("已收款",users.getUserid());
        request.setAttribute("username",username);
        request.setAttribute("ordersList1",ordersList1);
        request.setAttribute("ordersList2",ordersList2);
        request.setAttribute("ordersList3",ordersList3);
        request.getRequestDispatcher("/userinfor.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
