package servlet;

import bean.PageObject;
import bean.PaidType;
import bean.degrees;
import com.alibaba.fastjson.JSONObject;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class paidtypeservlet extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            request.getRequestDispatcher("/paidtypeAdd.jsp").forward(request, response);
        }
        if(action.equals("add")){//新增
            String paidtypename = request.getParameter("paidtypename").trim();
            PaidType d = new PaidType();
            d.setPaidtypename(paidtypename);
            basedao.save15(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            int paidtypeid = Integer.parseInt(request.getParameter("paidtypeid"));
            basedao.del15(paidtypeid);
            list(request,response);
        }else if(action.equals("update")){
            int paidtypeid = Integer.parseInt(request.getParameter("paidtypeid"));
            PaidType paidType = basedao.findByStudId15(paidtypeid);
            request.setAttribute("paidType", paidType);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/paidtypeUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int paidtypeid =Integer.parseInt(request.getParameter("paidtypeid"));
            String paidtypename = request.getParameter("paidtypename").trim();
            PaidType d = new PaidType();
            d.setPaidtypeid(paidtypeid);
            d.setPaidtypename(paidtypename);
            basedao.update15(d);
            list(request,response);
        }
    }
    //分页查询
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if(page==null){
            page = "1";
        }
        if(limit==null){
            limit = "10";
        }
        //查询列表
        List<PaidType> paidTypeList = basedao.findByPage15(Integer.parseInt(limit),Integer.parseInt(page));
        //查询列表
        JSONObject json =new JSONObject();
        json.put("msg","");
        json.put("code",0);
        json.put("count",1000);
        json.put("data",paidTypeList);
        response.getWriter().println(json.toJSONString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
