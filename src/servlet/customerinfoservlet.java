package servlet;

import bean.CustomerInfo;
import bean.PageObject;
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
 * Created by 邹周兴 on 2018/3/21.
 */
@WebServlet(name = "customerinfoservlet")
public class customerinfoservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action=request.getParameter("action");
                if(action==null){
                   action = "list";
                }
            if(action.equals("init")){
                List<Users> userList = baseDAO.findUserAll3();
                request.setAttribute("userList",userList);
                List<CustomerInfo> CustomerInfoList = baseDAO.findcustomer7();
                request.setAttribute("CustomerInfoList",CustomerInfoList);
                request.getRequestDispatcher("/CustomerInfoAdd.jsp").forward(request, response);//需改jsp页面
                //新增
            }else if(action.equals("add")){//增加
                    String custname=request.getParameter("custname").trim();
                    String custtype=request.getParameter("custtype").trim();
                    String bankAccount=request.getParameter("bankAccount").trim();
                    String bankName=request.getParameter("bankName").trim();
                    String contact=request.getParameter("Contact").trim();
                    String phone=request.getParameter("Phone").trim();
                    String ticketName=request.getParameter("TicketName").trim();
                    String ticketAddr=request.getParameter("TicketAddr").trim();
                    String ticketTel=request.getParameter("TicketTel").trim();
                    String taxNo=request.getParameter("TaxNo").trim();
                    String custState=request.getParameter("custState").trim();
                    String username=request.getParameter("username").trim();
                    String source=request.getParameter("source").trim();


                    CustomerInfo d = new CustomerInfo();
                    d.setCustname(custname);
                    d.setCusttype(custtype);
                    d.setBankAccount(bankAccount);
                    d.setBankName(bankName);
                    d.setContact(contact);
                    d.setPhone(phone);
                    d.setTicketName(ticketName);
                    d.setTicketAddr(ticketAddr);
                    d.setTicketTel(ticketTel);
                    d.setTaxNo(taxNo);
                    d.setCustState(custState);
                    d.setUsername(username);
                    d.setSource(source);
                    baseDAO.save7(d);
                    list(request,response);
                }else if(action.equals("list")){//列表
                    list(request,response);
                }else if(action.equals("del")){//删除
                    String custId = request.getParameter("custId");
                    baseDAO.del7(custId);
                    list(request,response);
                }else if(action.equals("update")){
                    String custId = request.getParameter("custId");
                    CustomerInfo customerInfo = baseDAO.findByStudId7(custId);
                    //转向到列表页面,这个语句相当于jsp:forward动作元素
                    request.setAttribute("customerInfo",customerInfo);
                    List<Users> usersList = baseDAO.findUserAll3();
                    request.setAttribute("usersList",usersList);
                    request.getRequestDispatcher("/customerUpdate.jsp").forward(request, response);
                }else if(action.equals("updateSave")){
                    int custid = Integer.parseInt(request.getParameter("custId"));
                    String custname = request.getParameter("custname").trim();
                    String custtype = request.getParameter("custtype").trim();
                    String bankAccount = request.getParameter("bankAccount").trim();
                    String bankName = request.getParameter("bankName").trim();
                    String contact = request.getParameter("Contact").trim();
                    String phone = request.getParameter("Phone").trim();
                    String ticketName = request.getParameter("TicketName").trim();
                    String ticketAddr = request.getParameter("TicketAddr").trim();
                    String ticketTel = request.getParameter("TicketTel").trim();
                    String taxNo = request.getParameter("TaxNo").trim();
                    String custState = request.getParameter("custState").trim();
                    String username = request.getParameter("username").trim();
                    String source = request.getParameter("source").trim();

                    CustomerInfo d = new CustomerInfo();
                    d.setCustId(custid);
                    d.setCustname(custname);
                    d.setCusttype(custtype);
                    d.setBankAccount(bankAccount);
                    d.setBankName(bankName);
                    d.setContact(contact);
                    d.setPhone(phone);
                    d.setTicketName(ticketName);
                    d.setTicketAddr(ticketAddr);
                    d.setTicketTel(ticketTel);
                    d.setTaxNo(taxNo);
                    d.setCustState(custState);
                    d.setUsername(username);
                    d.setSource(source);
                    baseDAO.update7(d);
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
            if(opr==null){
                opr="first";
            }
            //第一步：计算总记录数
            int cnt =baseDAO.findCount7();
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
            List<CustomerInfo> CustomerInfoList = baseDAO.findByPage7(pager.getPageSize(), pager.getCurPage());
            //定义request变量，在jsp页面中使用
            request.setAttribute("CustomerInfoList", CustomerInfoList);
            //分页参数
            request.setAttribute("pager", pager);
            //转向到列表页面,这个语句相当于jsp:forward动作元素find
            request.getRequestDispatcher("/CustomerInfoList.jsp").forward(request, response);
        }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doPost(request,response);
        }
}
