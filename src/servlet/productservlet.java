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
 * Created by 曾鑫 on 2018/3/20.
 */
@WebServlet(name = "productservlet")
public class productservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "list";
        }
        if(action.equals("init")){
            List<Unit> unitList = baseDAO.findunit4();//需改baseDAO
            request.setAttribute("unitList", unitList);
            List<supplier> supplierList = baseDAO.findsupplier5();//需改baseDAO
            request.setAttribute("supplierList", supplierList);
            List<Product> productList = baseDAO.findproduct6();
            request.setAttribute("productList",productList);
            request.getRequestDispatcher("/productAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//新增
            String prodid = request.getParameter("prodid");
            String prodname = request.getParameter("prodname").trim();
            String prodModel = request.getParameter("prodModel").trim();
            int prodUnit = Integer.parseInt(request.getParameter("prodUnit"));
            String prodStyle = request.getParameter("prodStyle").trim();
            float prodCount = Float.parseFloat(request.getParameter("prodCount").trim());
            float inPrice = Float.parseFloat(request.getParameter("inPrice").trim());
            float salePrice = Float.parseFloat(request.getParameter("salePrice").trim());
            float lowSalePrice = Float.parseFloat(request.getParameter("lowSalePrice").trim());
            String prodSerial = request.getParameter("prodSerial").trim();
            String cdKey = request.getParameter("cdKey").trim();
            String saleStatus = request.getParameter("saleStatus").trim();
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            String remark = request.getParameter("remark").trim();
            Product d = new Product();
            d.setProdid(prodid);
            d.setProdname(prodname);
            d.setProdModel(prodModel);
            d.setProdUnit(prodUnit);
            d.setProdStyle(prodStyle);
            d.setProdCount(prodCount);
            d.setInPrice(inPrice);
            d.setSalePrice(salePrice);
            d.setLowSalePrice(lowSalePrice);
            d.setProdSerial(prodSerial);
            d.setCdKey(cdKey);
            if(saleStatus.equals("未售")){//销售状态
                d.setSaleStatus("0");//1：已售  0：未售
            }else{
                d.setSaleStatus("1");
            }
            d.setSupplierId(supplierId);
            d.setRemark(remark);
            baseDAO.save6(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String prodid = request.getParameter("prodid");
            baseDAO.del6(prodid);
            list(request,response);
        }else if(action.equals("update")){
            List<Unit> unitList = baseDAO.findunit4();
            request.setAttribute("unitList", unitList);
            List<supplier> supplierList = baseDAO.findsupplier5();
            request.setAttribute("supplierList", supplierList);
            String prodid = request.getParameter("prodid");
            Product product = baseDAO.findByStudId6(prodid);
            request.setAttribute("product", product);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/productUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            String prodid = request.getParameter("prodid").trim();
            String prodname = request.getParameter("prodname").trim();
            String prodModel = request.getParameter("prodModel").trim();
            int prodUnit = Integer.parseInt(request.getParameter("prodUnit"));
            String prodStyle = request.getParameter("prodStyle").trim();
            float prodCount = Float.parseFloat(request.getParameter("prodCount").trim());
            float inPrice = Float.parseFloat(request.getParameter("inPrice").trim());
            float salePrice = Float.parseFloat(request.getParameter("salePrice").trim());
            float lowSalePrice = Float.parseFloat(request.getParameter("lowSalePrice").trim());
            String prodSerial = request.getParameter("prodSerial").trim();
            String cdKey = request.getParameter("cdKey").trim();
            String saleStatus = request.getParameter("saleStatus").trim();
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            String  remark= request.getParameter("remark").trim();

            Product d = new Product();
            d.setProdid(prodid);
            d.setProdUnit(prodUnit);
            d.setProdStyle(prodStyle);
            d.setProdname(prodname);
            d.setProdModel(prodModel);
            d.setProdCount(prodCount);
            d.setInPrice(inPrice);
            d.setSalePrice(salePrice);
            d.setLowSalePrice(lowSalePrice);
            d.setProdSerial(prodSerial);
            d.setCdKey(cdKey);
            if(saleStatus.equals("未售")){
                d.setSaleStatus("0");//1：已售  0：未售
            }else{
                d.setSaleStatus("1");
            }
            d.setSupplierId(supplierId);
            d.setRemark(remark);
            baseDAO.update6(d);
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
        int cnt =baseDAO.findCount6();
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
        List<Product> productList = baseDAO.findByPage6(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("productList", productList);
        //分页参数
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/productList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
