package servlet;

import bean.*;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class financeservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("userinfor");
        if(action==null){
            action = "list";
        }
        if(action.equals("init")){
            List<Orders> ordersList = baseDAO.findorders10();
            request.setAttribute("ordersList",ordersList);
            List<PaidType> paidTypeList = baseDAO.findpaidtype15();
            request.setAttribute("paidTypeList",paidTypeList);
            List<Product> productList = baseDAO.findproduct6();
            request.setAttribute("productList",productList);
            request.getRequestDispatcher("/financeAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//增加
            String orderId =request.getParameter("orderId").trim();
            int prodid =Integer.parseInt(request.getParameter("prodid").trim());
            String paidtypeid =request.getParameter("paidtypeid").trim();
            Float remainMoney =Float.parseFloat(request.getParameter("remainMoney").trim());
            Float paidMoney =Float.parseFloat(request.getParameter("paidMoney"));
            Float orderMoney =Float.parseFloat(request.getParameter("orderMoney"));
            String paidPerson =request.getParameter("paidPerson");
            String inbank =request.getParameter("inbank");
            String bankAccount =request.getParameter("bankAccount");
            String outbank =request.getParameter("outbank");
            String warrant =request.getParameter("warrant");
            String paidTime =request.getParameter("paidTime");
            String paidinTime =request.getParameter("paidinTime");
            String invalid =request.getParameter("invalid");
            String username =users.getUsername();
            String oprtime =request.getParameter("oprtime").trim();
            String oprType=request.getParameter("oprType").trim();
            finance d = new finance();
            d.setOrderId(orderId);
            d.setProdid(prodid);
            d.setPaidtypeid(paidtypeid);
            d.setRemainMoney(remainMoney);
            d.setPaidMoney(paidMoney);
            d.setOrderMoney(orderMoney);
            d.setPaidPerson(paidPerson);
            d.setInbank(inbank);
            d.setBankAccount(bankAccount);
            d.setOutbank(outbank);
            d.setWarrant(warrant);
            d.setPaidTime(paidTime);
            d.setPaidinTime(paidinTime);
            d.setInvalid(invalid);
            d.setUsername(username);
            d.setOprtime(oprtime);
            d.setOprType(oprType);
            baseDAO.save16(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String financeId = request.getParameter("financeId");
            baseDAO.del16(financeId);
            list(request,response);
        }else if(action.equals("update")){
            List<Orders> ordersList = baseDAO.findorders10();
            request.setAttribute("ordersList",ordersList);
            List<PaidType> paidTypeList = baseDAO.findpaidtype15();
            request.setAttribute("paidTypeList",paidTypeList);
            String financeId = request.getParameter("financeId");
            finance finance = baseDAO.findByStudId16(financeId);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.setAttribute("finance",finance);
            request.getRequestDispatcher("/financeUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int financeId =Integer.parseInt(request.getParameter("financeId").trim());
            String orderId =request.getParameter("orderId").trim();
            int prodid =Integer.parseInt(request.getParameter("prodid").trim());
            String paidtypeid =request.getParameter("paidtypeid").trim();
            Float remainMoney =Float.parseFloat(request.getParameter("remainMoney").trim());
            Float paidMoney =Float.parseFloat(request.getParameter("paidMoney"));
            Float orderMoney =Float.parseFloat(request.getParameter("orderMoney"));
            String paidPerson =request.getParameter("paidPerson");
            String inbank =request.getParameter("inbank");
            String bankAccount =request.getParameter("bankAccount");
            String outbank =request.getParameter("outbank");
            String warrant =request.getParameter("warrant");
            String paidTime =request.getParameter("paidTime");
            String paidinTime =request.getParameter("paidinTime");
            String invalid =request.getParameter("invalid");
            String username =users.getUsername();
            String oprtime =request.getParameter("oprtime").trim();
            String oprType=request.getParameter("oprType").trim();
            finance d = new finance();
            d.setFinanceId(financeId);
            d.setOrderId(orderId);
            d.setProdid(prodid);
            d.setPaidtypeid(paidtypeid);
            d.setRemainMoney(remainMoney);
            d.setPaidMoney(paidMoney);
            d.setOrderMoney(orderMoney);
            d.setPaidPerson(paidPerson);
            d.setInbank(inbank);
            d.setBankAccount(bankAccount);
            d.setOutbank(outbank);
            d.setWarrant(warrant);
            d.setPaidTime(paidTime);
            d.setPaidinTime(paidinTime);
            d.setInvalid(invalid);
            d.setUsername(username);
            d.setOprtime(oprtime);
            d.setOprType(oprType);
            baseDAO.update16(d);
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
        int cnt =baseDAO.findCount16();
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
        List<finance> financeList = baseDAO.findByPage16(pager.getPageSize(), pager.getCurPage(),OrderId);
        request.setAttribute("OrderId",OrderId);
        //定义request变量，在jsp页面中使用
        request.setAttribute("financeList", financeList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/financeList.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
