package servlet;

import bean.Users;
import bean.contract;
import bean.contractAttach;
import com.oreilly.servlet.MultipartRequest;
import common.ContextUtils;
import common.RenamePolicy;
import db.BaseDAO;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


public class contractattachservlet extends HttpServlet {
    BaseDAO baseDAO = new BaseDAO();
    private  String contid;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String filepath = request.getRealPath("/")+"filepath";
        System.out.println(filepath);
        //判断文件是否存在
        File file = new File(filepath);
        if(!file.exists()){
            //创建文件夹
            file.mkdir();
        }
        //设置文件大小
        int filesize = 1024*1024*3*8;
        //设置文件上传名称
        String filename = "";
        RenamePolicy rename = new RenamePolicy();
        //上传文件
        MultipartRequest mult = null;
        String action="";
        try {
            mult = new MultipartRequest(request,filepath,filesize,"UTF-8",rename);
            action = mult.getParameter("action");
        } catch (Exception e) {
//            e.printStackTrace();
        }
        if(action==""){
            action=request.getParameter("action");
        }
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("userinfor");
        contid = request.getParameter("contract_id");
        if(contid!=null){
            session.setAttribute("contract_id",contid);
        }else {
            contid= (String) session.getAttribute("contract_id");
        }
        if(action==null){
            action = "list";
        }
        if(action.equals("init")){
            List<contract> contractList = baseDAO.findcontract8();
            request.setAttribute("contractList",contractList);
            request.getRequestDispatcher("/contractattachAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){//增加
            Enumeration en = mult.getFileNames();
            while(en.hasMoreElements()){
                String name = (String)en.nextElement();
                filename = mult.getFilesystemName(name);
                System.out.println(filename);
            }
            String contract_id =(String) session.getAttribute("contract_id");
            int seq =Integer.parseInt(mult.getParameter("Seq").trim());
            String attachFile =mult.getParameter("AttachFile").trim();
            String uploadTime = ContextUtils.dateToStrShort(new Date());
            String user_name =users.getUsername();
            contractAttach d = new contractAttach();
            d.setContract_id(Integer.parseInt(contract_id));
            d.setSeq(seq);
            d.setAttachFile(attachFile);
            d.setUploadTime(uploadTime);
            d.setUser_name(user_name);
            d.setAttachURL("/"+filename);
            baseDAO.save9(d);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String contractAttach_id = request.getParameter("contractAttach_id");
            baseDAO.del9(contractAttach_id);
            list(request,response);
        }else if(action.equals("update")){
            String contractAttach_id = request.getParameter("contractAttach_id");
            contractAttach contractAttach = baseDAO.findByStudId9(contractAttach_id);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.setAttribute("contractAttach",contractAttach);
            List<contract> contractList = baseDAO.findcontract8();
            request.setAttribute("contractList",contractList);
            request.getRequestDispatcher("/contractattachUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            Enumeration en = mult.getFileNames();
            while(en.hasMoreElements()){
                String name = (String)en.nextElement();
                filename = mult.getFilesystemName(name);
                System.out.println(filename);
            }
            int contractAttach_id=Integer.parseInt(mult.getParameter("contractAttach_id"));
            int contract_id =Integer.parseInt(mult.getParameter("contract_id").trim());
            int seq =Integer.parseInt(mult.getParameter("Seq").trim());
            String attachFile =mult.getParameter("AttachFile").trim();
            String user_name =users.getUsername();
            contractAttach d = new contractAttach();
            d.setContractAttach_id(contractAttach_id);
            d.setContract_id(contract_id);
            d.setSeq(seq);
            d.setAttachFile(attachFile);
            d.setUser_name(user_name);
            d.setAttachURL("/"+filename);
            baseDAO.update9(d);
            list(request,response);
        }else if(action.equals("img")){
            //图片预览
            List<contractAttach> attachVos=baseDAO.findcontract9(contid);
            request.setAttribute("attachVos",attachVos);
            request.getRequestDispatcher("imglist.jsp").forward(request,response);
        }
    }
    //分页查询
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<contract> contractList = baseDAO.findcontract8();
        request.setAttribute("contractList",contractList);
        //查询列表
        List<contractAttach> contractattachList = baseDAO.findByPage9(1000, 1, Integer.parseInt(contid));
        //定义request变量，在jsp页面中使用
        request.setAttribute("contractattachList", contractattachList);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/contractattachList.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
