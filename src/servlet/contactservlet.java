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
 * Created by 曾鑫 on 2018/3/29.
 */
@WebServlet(name = "contactservlet")
public class contactservlet extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            List<CustomerInfo> customerInfoList = basedao.findcustomer7();
            request.setAttribute("customerInfoList",customerInfoList);
            List<Users> usersList = basedao.findUserAll3();
            request.setAttribute("usersList",usersList);
            request.getRequestDispatcher("/ContactAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//新增
            String talkDate = request.getParameter("talkDate").trim();
            String custContact = request.getParameter("custContact").trim();
            String phone1 = request.getParameter("phone1");
            String phone2 = request.getParameter("phone2").trim();
            int custid = Integer.parseInt(request.getParameter("custid"));
            String qqCode = request.getParameter("qqCode");
            String email = request.getParameter("email").trim();
            String weixin = request.getParameter("weixin").trim();
            int userid = Integer.parseInt(request.getParameter("userid"));
            String birthday = request.getParameter("birthday").trim();
            String hobbit = request.getParameter("hobbit").trim();
            String jobName = request.getParameter("jobName");
            String remark = request.getParameter("remark").trim();
            Contact d = new Contact();
            d.setTalkDate(talkDate);
            d.setCustContact(custContact);
            d.setPhone1(phone1);
            d.setPhone2(phone2);
            d.setCustid(custid);
            d.setQqCode(qqCode);
            d.setEmail(email);
            d.setWeixin(weixin);
            d.setUserid(userid);
            d.setBirthday(birthday);
            d.setHobbit(hobbit);
            d.setJobName(jobName);
            d.setRemark(remark);
            basedao.save14(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String contactId = request.getParameter("contactId");
            basedao.del14(contactId);
            list(request,response);
        }else if(action.equals("update")){
            String contactId = request.getParameter("contactId");
            Contact contact = basedao.findById14(contactId);
            request.setAttribute("contact", contact);
            List<CustomerInfo> customerInfoList = basedao.findcustomer7();
            request.setAttribute("customerInfoList",customerInfoList);
            List<Users> usersList = basedao.findUserAll3();
            request.setAttribute("usersList",usersList);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/ContactUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int contactId = Integer.parseInt(request.getParameter("contactId"));
            String talkDate = request.getParameter("talkDate").trim();
            String custContact = request.getParameter("custContact").trim();
            String phone1 = request.getParameter("phone1");
            String phone2 = request.getParameter("phone2").trim();
            int custid = Integer.parseInt(request.getParameter("custid"));
            String qqCode = request.getParameter("qqCode");
            String email = request.getParameter("email").trim();
            String weixin = request.getParameter("weixin").trim();
            int userid = Integer.parseInt(request.getParameter("userid"));
            String birthday = request.getParameter("birthday").trim();
            String hobbit = request.getParameter("hobbit").trim();
            String jobName = request.getParameter("jobName");
            String remark = request.getParameter("remark").trim();
            Contact d = new Contact();
            d.setContactId(contactId);
            d.setTalkDate(talkDate);
            d.setCustContact(custContact);
            d.setPhone1(phone1);
            d.setPhone2(phone2);
            d.setCustid(custid);
            d.setQqCode(qqCode);
            d.setEmail(email);
            d.setWeixin(weixin);
            d.setUserid(userid);
            d.setBirthday(birthday);
            d.setHobbit(hobbit);
            d.setJobName(jobName);
            d.setRemark(remark);
            basedao.update14(d);
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
            opr = "first";
        }

        //第一步：计算总记录数
        int cnt = basedao.findCount14();
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
        List<Contact> contactList= basedao.findByPage14(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("contactList", contactList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/ContactList.jsp").forward(request, response);//需改jsp页面
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
