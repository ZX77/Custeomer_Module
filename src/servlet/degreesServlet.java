package servlet;

import bean.PageObject;
import bean.Unit;
import bean.degrees;
import com.alibaba.fastjson.JSONObject;
import db.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 林longqi on 2018/3/21.
 */
@WebServlet(name = "degreesServlet")
public class degreesServlet extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            request.getRequestDispatcher("/degreesAdd.jsp").forward(request, response);
        }
        if(action.equals("add")){//新增
            String degreename = request.getParameter("degreename").trim();
            degrees d = new degrees();
            d.setDegreename(degreename);
            basedao.save2(d);
            request.getRequestDispatcher("/degreesList.jsp").forward(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            int degreeid = Integer.parseInt(request.getParameter("degreeid"));
            basedao.del2(degreeid);
            request.getRequestDispatcher("/degreesList.jsp").forward(request,response);
        }else if(action.equals("update")){
            int degreeid = Integer.parseInt(request.getParameter("degreeid"));
            degrees degrees = basedao.findByStudId2(degreeid);
            request.setAttribute("degrees", degrees);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/degreesUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int degreeid =Integer.parseInt(request.getParameter("degreeid"));
            String degreename = request.getParameter("degreename").trim();
            degrees d = new degrees();
            d.setDegreeid(degreeid);
            d.setDegreename(degreename);
            basedao.update2(d);
            request.getRequestDispatcher("/degreesList.jsp").forward(request,response);
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
        List<degrees> degreesList = basedao.findByPage2(Integer.parseInt(limit),Integer.parseInt(page));
        //查询列表
        JSONObject json =new JSONObject();
        json.put("msg","");
        json.put("code",0);
        json.put("count",1000);
        json.put("data",degreesList);
        response.getWriter().println(json.toJSONString());
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
