package servlet;

import bean.*;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class jobrecordservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action = "list";
        }
        if(action.equals("init")){
            List<CustomerInfo> custList = baseDAO.findcustomer7();
            request.setAttribute("custList",custList);
            List<Users> usersList = baseDAO.findUserAll3();
            request.setAttribute("usersList",usersList);
            List<Product> proList = baseDAO.findproduct6();
            request.setAttribute("proList",proList);
            request.getRequestDispatcher("/jobRecordAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//增加
            String orderId =request.getParameter("orderId").trim();
            String jobDate =request.getParameter("jobDate").trim();
            String prodName =request.getParameter("prodName").trim();
            int custid =Integer.parseInt(request.getParameter("custid"));
            String jobContent =request.getParameter("jobContent").trim();
            String callback =request.getParameter("callback").trim();
            String userid =request.getParameter("userid").trim();
            String custEval =request.getParameter("custEval").trim();
            String custSign =request.getParameter("custSign").trim();
            String startTime =request.getParameter("startTime").trim();
            String endTime=request.getParameter("endTime").trim();
            int workDay =Integer.parseInt(request.getParameter("workDay").trim());
            Float busMoney=Float.parseFloat(request.getParameter("busMoney").trim());
            Float attachMoney =Float.parseFloat(request.getParameter("attachMoney").trim());
            jobRecord d = new jobRecord();
            d.setOrderId(orderId);
            d.setJobDate(jobDate);
            d.setProdName(prodName);
            d.setCustid(custid);
            d.setJobContent(jobContent);
            d.setCallback(callback);
            d.setUserid(userid);
            d.setCustEval(custEval);
            if(custSign.equals("未签")){//销售状态
                d.setCustSign("0");//  0：未签
            }else{
                d.setCustSign("1");//  1：已签
            }
            d.setStartTime(startTime);
            d.setEndTime(endTime);
            d.setWorkDay(workDay);
            d.setBusMoney(busMoney);
            d.setAttachMoney(attachMoney);
            baseDAO.save12(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String jobId = request.getParameter("jobId");
            baseDAO.del12(jobId);
            list(request,response);
        }else if(action.equals("update")){
            String jobId = request.getParameter("jobId");
            jobRecord job = baseDAO.findByStudId12(jobId);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.setAttribute("job",job);
            List<Product> prodList = baseDAO.findproduct6();
            request.setAttribute("prodList", prodList);
            List<Users> usersList = baseDAO.findUserAll3();
            request.setAttribute("usersList",usersList);
            List<CustomerInfo> custList = baseDAO.findcustomer7();
            request.setAttribute("custList", custList);
            request.getRequestDispatcher("/jobRecordUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int jobId = Integer.parseInt(request.getParameter("jobId"));
            String orderId = request.getParameter("orderId").trim();
            String jobDate= request.getParameter("jobDate").trim();
            String prodName = request.getParameter("prodName").trim();
            int custid =Integer.parseInt( request.getParameter("custid").trim());
            String jobContent = request.getParameter("jobContent").trim();
            String callback = request.getParameter("callback").trim();
            String  userid = request.getParameter("userid").trim();
            String custEval = request.getParameter("custEval").trim();
            String custSign = request.getParameter("custSign").trim();
            String startTime= request.getParameter("startTime").trim();
            String endTime = request.getParameter("endTime").trim();
            int workDay = Integer.parseInt(request.getParameter("workDay").trim());
            Float busMoney = Float.parseFloat(request.getParameter("busMoney").trim());
            Float attachMoney =Float.parseFloat(request.getParameter("attachMoney").trim());
            jobRecord d = new jobRecord();
            d.setJobId(jobId);
            d.setOrderId(orderId);
            d.setJobDate(jobDate);
            d.setProdName(prodName);
            d.setCustid(custid);
            d.setJobContent(jobContent);
            d.setCallback(callback);
            d.setUserid(userid);
            d.setCustEval(custEval);
            d.setCustSign(custSign);
            d.setStartTime(startTime);
            d.setEndTime(endTime);
            d.setWorkDay(workDay);
            d.setBusMoney(busMoney);
            d.setAttachMoney(attachMoney);
            baseDAO.update12(d);
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
        int cnt =baseDAO.findCount12();
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

        //根据条件查询派工信息
        String select = request.getParameter("select");
        List<jobRecord> jobRecordList = null;
        if(select==null){
            //查询列表
            jobRecordList = baseDAO.findByPage12(pager.getPageSize(), pager.getCurPage());
        }else if(select.equals("select")){
            jobRecord jobRecord = new jobRecord();
            jobRecord.setCustid(Integer.parseInt(request.getParameter("custid")));
            jobRecord.setUserid(request.getParameter("userid"));
            request.setAttribute("jobRecord",jobRecord);
            jobRecordList = baseDAO.findByPage12(pager.getPageSize(), pager.getCurPage(),jobRecord);
        }

        //定义request变量，在jsp页面中使用
        request.setAttribute("jobRecordList", jobRecordList);
        List<CustomerInfo> custList = baseDAO.findcustomer7();
        request.setAttribute("custList",custList);
        List<Users> usersList = baseDAO.findUserAll3();
        request.setAttribute("usersList",usersList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/jobRecordList.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
