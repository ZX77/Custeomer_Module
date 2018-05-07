package servlet;

import bean.Ticket;
import bean.CustomerInfo;
import bean.PageObject;
import bean.Users;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ticketservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("userinfor");
        if(action==null){
            action = "list";
        }
        if(action.equals("init")){
            List<CustomerInfo> custList = baseDAO.findcustomer7();
            request.setAttribute("custList",custList);
            request.getRequestDispatcher("/TicketAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//增加
            String ticketDate =request.getParameter("ticketDate").trim();
            String orderid =request.getParameter("orderid").trim();
            int custid =Integer.parseInt(request.getParameter("custid").trim());
            Float ticketMoney =Float.parseFloat(request.getParameter("ticketMoney").trim());
            String ticketComp =request.getParameter("ticketComp");
            String username =users.getUsername();
            String oprtime =request.getParameter("oprtime").trim();
            String remark=request.getParameter("remark").trim();
            Ticket d = new Ticket();
            d.setTicketDate(ticketDate);
            d.setOrderid(orderid);
            d.setCustid(custid);
            d.setTicketMoney(ticketMoney);
            d.setTicketComp(ticketComp);
            d.setUsername(username);
            d.setOprtime(oprtime);
            d.setRemark(remark);
            baseDAO.save17(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String id = request.getParameter("id");
            baseDAO.del17(id);
            list(request,response);
        }else if(action.equals("update")){
            String id = request.getParameter("id");
            List<CustomerInfo> custList = baseDAO.findcustomer7();
            request.setAttribute("custList",custList);
            Ticket ticket = baseDAO.findByStudId17(id);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.setAttribute("ticket",ticket);
            request.getRequestDispatcher("/TicketUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int id =Integer.parseInt(request.getParameter("id").trim());
            String ticketDate =request.getParameter("ticketDate").trim();
            String orderid =request.getParameter("orderid").trim();
            int custid =Integer.parseInt(request.getParameter("custid").trim());
            Float ticketMoney =Float.parseFloat(request.getParameter("ticketMoney").trim());
            String ticketComp =request.getParameter("ticketComp");
            String username =users.getUsername();
            String oprtime =request.getParameter("oprtime").trim();
            String remark=request.getParameter("remark").trim();
            Ticket d = new Ticket();
            d.setId(id);
            d.setTicketDate(ticketDate);
            d.setOrderid(orderid);
            d.setCustid(custid);
            d.setTicketMoney(ticketMoney);
            d.setTicketComp(ticketComp);
            d.setUsername(username);
            d.setOprtime(oprtime);
            d.setRemark(remark);
            baseDAO.update17(d);
            list(request,response);
        }
    }
    //分页查询
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        PageObject pager = new PageObject();
        //读取页面的参数
        String opr = request.getParameter("opr");
        String curPage = request.getParameter("pagecur");
        String value = request.getParameter("val");
        int x=0;
        if(value==null){
            value = "1";
            x=1;
        }
        if(curPage==null){
            curPage="1";
        }
        if(opr==null){
            opr="first";
        }
        //第一步：计算总记录数
        int cnt =baseDAO.findCount17();
        //自动计算总页数
        pager.setRowCount(cnt);
        if(value.equals("") || x==1){
            //设置当前页
            pager.setCurPage(Integer.parseInt(curPage));
            //设置操作符
            pager.setOpr(opr);
        }else{
            pager.setCurPage(Integer.parseInt(value));
        }
        String OrderId = request.getParameter("OrderId");
        //查询列表
        List<Ticket> TicketList = baseDAO.findByPage17(pager.getPageSize(), pager.getCurPage(),OrderId);
        //定义request变量，在jsp页面中使用
        request.setAttribute("OrderId",OrderId);
        request.setAttribute("TicketList", TicketList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/TicketList.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
