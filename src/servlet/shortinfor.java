package servlet;

import bean.*;
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
@WebServlet(name = "shortinfor")
public class shortinfor extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String custname = request.getParameter("custname");
        request.setAttribute("custname",custname);
        //获取客户的订单信息
        CustomerInfo customerInfo = baseDAO.custinfor(custname);
        List<Orders> ordersList = baseDAO.findByPage10(customerInfo.getCustId());
        request.setAttribute("ordersList",ordersList);
        //获取客户的合同信息
        List<contract> contractList = baseDAO.findByPage8(customerInfo.getCustId());
        request.setAttribute("contractList",contractList);
        //获取客户的派工信息
        List<jobRecord> jobRecordList = baseDAO.findjobrecord(customerInfo.getCustId());
        request.setAttribute("jobRecordList",jobRecordList);
        //获取客户的洽谈信息
        List<business> businessList = baseDAO.findbusiness(customerInfo.getCustId());
        request.setAttribute("businessList",businessList);
        request.getRequestDispatcher("/test.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
