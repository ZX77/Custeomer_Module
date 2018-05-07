package servlet;

import bean.PageObject;
import bean.Users;
import bean.contract;
import bean.CustomerInfo;
import common.ContextUtils;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by 邹周兴 on 2018/3/21.
 */
@WebServlet(name = "contractservlet")
public class contractservlet extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    String oprtime;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("userinfor");
        if(action==null) {
            action = "list";
        }

        if(action.equals("init")){
            List<CustomerInfo> customerInfoListList = basedao.findcustomer7();
            request.setAttribute("customerInfoListList", customerInfoListList);
            List<Users> usersList = basedao.findUserAll3();
            request.setAttribute("usersList", usersList);
            request.getRequestDispatcher("/contractAdd.jsp").forward(request, response);//需改jsp页面
            //新增
    } if(action.equals("add")){
            String contract_no = request.getParameter("contract_no").trim();
            int custId = Integer.parseInt(request.getParameter("custId"));
            String contract_time = request.getParameter("contract_time");
            String due_time= request.getParameter("due_time").trim();
            String total_money = request.getParameter("total_money").trim();
            String deposit = request.getParameter("deposit").trim();
            String pay_type= request.getParameter("pay_type").trim();
            String status = request.getParameter("status").trim();
            int empid = Integer.parseInt(request.getParameter("empid"));
            String operator = users.getUsername();
            oprtime = ContextUtils.dateToStrShort(new Date());
            contract d =new contract();
            d.setContract_no(contract_no);
            d.setCustId(custId);
            d.setContract_time(contract_time);
            d.setDue_time(due_time);
            d.setTotal_money(total_money);
            d.setDeposit(deposit);
            d.setPay_type(pay_type);
            d.setStatus(status);
            d.setEmpid(empid);
            d.setOperator(operator);
            d.setOprtime(oprtime);
            basedao.save8(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String contract_id = request.getParameter("contract_id");
            basedao.del8(contract_id);
            list(request,response);
        }else if(action.equals("update")){
            String contract_id = request.getParameter("contract_id");
            contract contract = basedao.findByStudId8(contract_id);
            request.setAttribute("contract", contract);
            List<CustomerInfo> customerInfoListList = basedao.findcustomer7();
            request.setAttribute("customerInfoListList", customerInfoListList);
            List<Users> usersList = basedao.findUserAll3();
            request.setAttribute("usersList", usersList);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/contractUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int contract_id = Integer.parseInt(request.getParameter("contract_id"));
            String contract_no = request.getParameter("contract_no");
            int custId = Integer.parseInt(request.getParameter("custId"));
            String contract_time = request.getParameter("contract_time").trim();
            String due_time = request.getParameter("due_time").trim();
            String total_money = request.getParameter("total_money").trim();
            String deposit = request.getParameter("deposit").trim();
            String pay_type = request.getParameter("pay_type").trim();
            String status = request.getParameter("status").trim();
            int empid = Integer.parseInt(request.getParameter("empid"));
            contract d = new contract();
            d.setContract_id(contract_id);
            d.setContract_no(contract_no);
            d.setCustId(custId);
            d.setContract_time(contract_time);
            d.setDue_time(due_time);
            d.setTotal_money(total_money);
            d.setDeposit(deposit);
            d.setPay_type(pay_type);
            d.setStatus(status);
            d.setEmpid(empid);
            basedao.update8(d);
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
        int cnt = basedao.findCount10();
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

        String param = request.getParameter("param");
        System.out.print("param"+param);
        List<contract> contractsList = null;
        String custid = null;
        if(param==null){
             contractsList= basedao.findByPage8(pager.getPageSize(), pager.getCurPage());
        }else if(param.equals("select")){
            custid = request.getParameter("selected");
                if(custid.equals("0")){
                    contractsList= basedao.findByPage8(pager.getPageSize(), pager.getCurPage());
                }else {
                    contractsList = basedao.findByPages8(1000,1, Integer.parseInt(custid));
                }
        }
        //定义变量
        request.setAttribute("custid",custid);
        //查询列表
        //定义request变量，在jsp页面中使用
        request.setAttribute("contractsList", contractsList);
        List<contract>  contract = basedao.findcontract8();
        List<CustomerInfo> customerInfoList = basedao.findcustomer7();
        request.setAttribute("customerInfoList",customerInfoList);
        //分页参数
        request.setAttribute("pager", pager);

        request.setAttribute("contract",contract);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/contractList.jsp").forward(request, response);//需改jsp页面
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
