package servlet;


import bean.PageObject;
import bean.supplier;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 曾鑫 on 2018/3/20.
 */
@WebServlet(name = "supplierservlet")
public class supplierservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            request.getRequestDispatcher("/supplierAdd.jsp").forward(request, response);
        }
        if(action.equals("add")){//新增
            String supplierName= request.getParameter("supplierName").trim();
            String bankAccount = request.getParameter("bankAccount").trim();
            String bankName = request.getParameter("bankName").trim();
            String contact = request.getParameter("contact").trim();
            String phone = request.getParameter("phone").trim();
            String addr = request.getParameter("addr").trim();
            String remark = request.getParameter("remark").trim();

            supplier d = new supplier();
            d.setSupplierName(supplierName);
            d.setBankAccount(bankAccount);
            d.setBankName(bankName);
            d.setContact(contact);
            d.setPhone(phone);
            d.setAddr(addr);
            d.setRemark(remark);
            baseDAO.save5(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String supplierId = request.getParameter("supplierId");
            baseDAO.supplier5(supplierId);
            list(request,response);
        }else if(action.equals("update")){
            String supplierId = request.getParameter("supplierId");
            supplier supplier = baseDAO.findByStudId5(supplierId);
            request.setAttribute("supplier", supplier);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/supplierUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            System.out.println("进来了");
            if(request.getParameter("supplierId")!=null||request.getParameter("supplierId").equals("")) {
                System.out.println("有id数据");
            }else {
                System.out.println("您没有id数据");
                request.getRequestDispatcher("/supplierUpdate.jsp").forward(request, response);
            }
                String ss=request.getParameter("supplierId");
                int supplierId=Integer.parseInt(ss);
                System.out.println(supplierId);

            String supplierName = request.getParameter("supplierName").trim();
            System.out.println("supplierName===="+supplierName);

            String bankAccount = request.getParameter("bankAccount").trim();
            System.out.println(bankAccount);
            String bankName = request.getParameter("bankName").trim();
            System.out.println(bankName);
            String contact = request.getParameter("contact").trim();
            System.out.println(contact);
            String phone = request.getParameter("phone").trim();
            System.out.println(phone);
            String addr = request.getParameter("addr").trim();
            System.out.println(addr);
            String  remark= request.getParameter("remark").trim();
            System.out.println(remark);
//
            supplier d = new supplier();
            d.setSupplierId(supplierId);
            d.setSupplierName(supplierName);
            d.setBankAccount(bankAccount);
            d.setBankName(bankName);
            d.setContact(contact);
            d.setPhone(phone);
            d.setAddr(addr);
            d.setRemark(remark);
            baseDAO.update5(d);
            list(request,response);
        }
    }
    //分页查询
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        PageObject pager = new PageObject();
        //读取页面的参数
        String opr = request.getParameter("opr");
        String curPage = request.getParameter("curPage");
        String value = request.getParameter("val");
        int x=0;
        if(value==null){
            value = "1";
            x=1;
        }
        if(curPage==null){
            curPage="1";
        }
        if(opr == null){
            opr = "first";
        }
        //第一步：计算总记录数
        int cnt =baseDAO.findCount5();
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
        List<supplier> List = baseDAO.findByPage5(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("supplierList", List);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/supplierList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
