package servlet;

import bean.PageObject;
import bean.Unit;
import com.alibaba.fastjson.JSONObject;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class unitservlet extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            request.getRequestDispatcher("/unitAdd.jsp").forward(request, response);
        }
        if(action.equals("add")){//新增
            String unitName = request.getParameter("unitName").trim();
            Unit d = new Unit();
            d.setUnitName(unitName);
            basedao.save4(d);
            request.getRequestDispatcher("/UnitList.jsp").forward(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            int unitId = Integer.parseInt(request.getParameter("unitId"));
            basedao.del4(unitId);
            request.getRequestDispatcher("/UnitList.jsp").forward(request,response);
        }else if(action.equals("update")){
            int unitId = Integer.parseInt(request.getParameter("unitId"));
            Unit unit = basedao.findByStudId4(unitId);
            request.setAttribute("unit", unit);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/unitUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int unitId =Integer.parseInt(request.getParameter("unitId"));
            String unitName = request.getParameter("unitName").trim();
            Unit d = new Unit();
            d.setUnitId(unitId);
            d.setUnitName(unitName);
            basedao.update4(d);
            request.getRequestDispatcher("/UnitList.jsp").forward(request,response);
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
        List<Unit> unitList = basedao.findByPage4(Integer.parseInt(limit),Integer.parseInt(page));
        //查询列表
        JSONObject json =new JSONObject();
        json.put("msg","");
        json.put("code",0);
        json.put("count",1000);
        json.put("data",unitList);
        response.getWriter().println(json.toJSONString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
