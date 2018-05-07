package servlet;

import bean.Dep;
import bean.PageObject;
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
@WebServlet(name = "depservlet")
public class depservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            request.getRequestDispatcher("/depAdd.jsp").forward(request, response);
        }
        if(action.equals("add")){//新增
            String depname = request.getParameter("depname").trim();
            String chairman = request.getParameter("chairman").trim();
            String desc = request.getParameter("description").trim();
            Dep d = new Dep();
            d.setDepname(depname);
            d.setChairman(chairman);
            d.setDescription(desc);
            baseDAO.save1(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String depid = request.getParameter("depid");
            baseDAO.del1(depid);
            list(request,response);
        }else if(action.equals("update")){
            String depid = request.getParameter("depid");
            Dep dep = baseDAO.findByStudId1(depid);
            request.setAttribute("dep", dep);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/depUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            String depid = request.getParameter("depid");
            String depname = request.getParameter("depname").trim();
            String chairman = request.getParameter("chairman").trim();
            String desc = request.getParameter("description").trim();

            Dep d = new Dep();
            d.setDepid(Integer.parseInt(depid));
            d.setDepname(depname);
            d.setChairman(chairman);
            d.setDescription(desc);
            baseDAO.update1(d);
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
        int cnt =baseDAO.findDepCount1();
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
        List<Dep> depList = baseDAO.findByPage1(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("depList",depList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/depfind.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
