package servlet;

import bean.*;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class businessservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action = "list";
        }
        if(action.equals("init")){
            List<CustomerInfo> custList = baseDAO.findcustomer7();
            request.setAttribute("custList",custList);
            List<Product> productList = baseDAO.findproduct6();
            request.setAttribute("productList",productList);
            List<Users> userList = baseDAO.findUserAll3();
            request.setAttribute("userList",userList);
            request.getRequestDispatcher("/businessAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//增加
            String busDate =request.getParameter("busDate").trim();
            String prodName =request.getParameter("prodName").trim();
            String chatContent =request.getParameter("chatContent").trim();
            String chatResult =request.getParameter("chatResult").trim();
            int custid =Integer.parseInt(request.getParameter("custid"));
            String custContact =request.getParameter("custContact").trim();
            String phone =request.getParameter("phone").trim();
            int userid =Integer.parseInt(request.getParameter("userid"));
            String module =request.getParameter("module").trim();
            String moduleState=request.getParameter("moduleState").trim();
            Float moduleMoney =Float.parseFloat(request.getParameter("moduleMoney").trim());
            String remark=request.getParameter("remark").trim();
            business d = new business();
            d.setBusDate(busDate);
            d.setProdName(prodName);
            d.setChatContent(chatContent);
            d.setChatResult(chatResult);
            d.setCustid(custid);
            d.setCustContact(custContact);
            d.setPhone(phone);
            d.setUserid(userid);
            d.setModule(module);
            d.setModuleState(moduleState);
            d.setModuleMoney(moduleMoney);
            d.setRemark(remark);
            baseDAO.save13(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String businessId = request.getParameter("businessId");
            baseDAO.del13(businessId);
            list(request,response);
        }else if(action.equals("update")){
            String businessId = request.getParameter("businessId");
            business business = baseDAO.findByStudId13(businessId);
            List<Product> productList = baseDAO.findproduct6();
            request.setAttribute("productList",productList);
            List<CustomerInfo> custList = baseDAO.findcustomer7();
            request.setAttribute("custList",custList);
            List<Users> userList = baseDAO.findUserAll3();
            request.setAttribute("userList",userList);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.setAttribute("business",business);
            request.getRequestDispatcher("/businessUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int businessId = Integer.parseInt(request.getParameter("businessId"));
            String busDate =request.getParameter("busDate").trim();
            String prodName =request.getParameter("prodName").trim();
            String chatContent =request.getParameter("chatContent").trim();
            String chatResult =request.getParameter("chatResult").trim();
            int custid =Integer.parseInt(request.getParameter("custid"));
            String custContact =request.getParameter("custContact").trim();
            String phone =request.getParameter("phone").trim();
            int userid =Integer.parseInt(request.getParameter("userid"));
            String module =request.getParameter("module").trim();
            String moduleState=request.getParameter("moduleState").trim();
            Float moduleMoney =Float.parseFloat(request.getParameter("moduleMoney").trim());
            String remark=request.getParameter("remark").trim();
            business d = new business();
            d.setBusinessId(businessId);
            d.setBusDate(busDate);
            d.setProdName(prodName);
            d.setChatContent(chatContent);
            d.setChatResult(chatResult);
            d.setCustid(custid);
            d.setCustContact(custContact);
            d.setPhone(phone);
            d.setUserid(userid);
            d.setModule(module);
            d.setModuleState(moduleState);
            d.setModuleMoney(moduleMoney);
            d.setRemark(remark);
            baseDAO.update13(d);
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
        int cnt =baseDAO.findCount13();
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

        //查询列表
        List<business> businessList = baseDAO.findByPage13(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("businessList", businessList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/businessList.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
