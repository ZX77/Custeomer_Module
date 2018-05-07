package servlet;

import bean.Users;
import bean.workday;
import bean.PageObject;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class workdayservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action = "list";
        }
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("userinfor");
        if(action.equals("init")){
            request.getRequestDispatcher("/workdayAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//增加
            String weekDate =request.getParameter("weekDate").trim();
            String workContent =request.getParameter("workContent").trim();
            String workReview =request.getParameter("workReview").trim();
            String question =request.getParameter("question").trim();
            String warning =request.getParameter("warning");
            String weekPlan =request.getParameter("weekPlan");
            String username =users.getUsername();
            String oprtime =request.getParameter("oprtime").trim();
            String remark=request.getParameter("remark").trim();
            workday d = new workday();
            d.setWeekDate(weekDate);
            d.setWorkContent(workContent);
            d.setWorkReview(workReview);
            d.setQuestion(question);
            d.setWarning(warning);
            d.setWeekPlan(weekPlan);
            d.setUsername(username);
            d.setOprtime(oprtime);
            d.setRemark(remark);
            baseDAO.save18(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String id = request.getParameter("id");
            baseDAO.del18(id);
            list(request,response);
        }else if(action.equals("update")){
            String id = request.getParameter("id");
            workday workday = baseDAO.findByStudId18(id);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.setAttribute("workday",workday);
            request.getRequestDispatcher("/workdayUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int id =Integer.parseInt(request.getParameter("id").trim());
            String weekDate =request.getParameter("weekDate").trim();
            String workContent =request.getParameter("workContent").trim();
            String workReview =request.getParameter("workReview").trim();
            String question =request.getParameter("question").trim();
            String warning =request.getParameter("warning");
            String weekPlan =request.getParameter("weekPlan");
            String username = users.getUsername();
            String oprtime =request.getParameter("oprtime").trim();
            String remark=request.getParameter("remark").trim();
            workday d = new workday();
            d.setId(id);
            d.setWeekDate(weekDate);
            d.setWorkContent(workContent);
            d.setWorkReview(workReview);
            d.setQuestion(question);
            d.setWarning(warning);
            d.setWeekPlan(weekPlan);
            d.setUsername(username);
            d.setOprtime(oprtime);
            d.setRemark(remark);
            baseDAO.update18(d);
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
        int cnt =baseDAO.findCount18();
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
        List<workday> workdayList = baseDAO.findByPage18(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("workdayList", workdayList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/workdayList.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
