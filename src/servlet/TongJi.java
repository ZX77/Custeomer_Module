package servlet;

import bean.CustomerInfo;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 曾鑫 on 2018/4/9.
 */
@WebServlet(name = "TongJi")
public class TongJi extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count1  = basedao.moneycount(1);
        int count2 = basedao.moneycount(2);
        int count3 = basedao.moneycount(3);
        int count4 = basedao.moneycount(4);
        int count5 = basedao.moneycount(5);
        request.setAttribute("count1",count1);
        request.setAttribute("count2",count2);
        request.setAttribute("count3",count3);
        request.setAttribute("count4",count4);
        request.setAttribute("count5",count5);
        request.getRequestDispatcher("/bingtu.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
