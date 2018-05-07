package servlet;

import bean.Dep;
import bean.PageObject;
import bean.Users;
import bean.degrees;
import db.BaseDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 邹周兴 on 2018/3/21.
 */
@WebServlet(name = "usersservlet")
public class usersservlet extends HttpServlet {
    BaseDAO basedao = new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //获取session对象的值
        HttpSession session = request.getSession();
        if(action==null){
           action="list";
        }
        if(action.equals("init")){
            List<Dep> depList = basedao.findDepAll3();
            request.setAttribute("depList", depList);
            List<degrees> degreesList = basedao.finddegrees2();
            request.setAttribute("degreesList",degreesList);
            request.getRequestDispatcher("/userAdd.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            int depid = Integer.parseInt(request.getParameter("depid"));
            int degreeid = Integer.parseInt(request.getParameter("degreeid"));
            String jobname = request.getParameter("jobname").trim();
            int managerType = Integer.parseInt(request.getParameter("managerType"));
            String mobile = request.getParameter("mobile").trim();
            String email = request.getParameter("email").trim();
            String qqcode = request.getParameter("qqcode").trim();
            String weixin = request.getParameter("weixin").trim();
            String cardno = request.getParameter("cardno").trim();
            String bankName = request.getParameter("bankName").trim();
            String bankCardNo = request.getParameter("bankCardNo").trim();
            String joinDate = request.getParameter("joinDate").trim();
            String workDate = request.getParameter("workDate").trim();
            String levelDate = request.getParameter("levelDate").trim();
            float baseSalary = Float.parseFloat(request.getParameter("baseSalary"));
            float degreeSalary = Float.parseFloat(request.getParameter("degreeSalary"));
            String addr = request.getParameter("addr").trim();
            String remark = request.getParameter("remark").trim();
            Users u = new Users();
            u.setUsername(username);
            u.setPassword(password);
            u.setDepid(depid);
            u.setDegreeid(degreeid);
            u.setJobname(jobname);
            u.setManagerType(managerType);
            u.setMobile(mobile);
            u.setEmail(email);
            u.setQqcode(qqcode);
            u.setWeixin(weixin);
            u.setCardno(cardno);
            u.setBankName(bankName);
            u.setBankCardNo(bankCardNo);
            u.setJoinDate(joinDate);
            u.setWorkDate(workDate);
            u.setLevelDate(levelDate);
            u.setBaseSalary(baseSalary);
            u.setDegreeSalary(degreeSalary);
            u.setAddr(addr);
            u.setStatus(1);//1有效，0：无效
            u.setRemark(remark);
            basedao.add3(u);
            list(request,response);
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            int userid = Integer.parseInt(request.getParameter("userid"));
            basedao.del3(userid);
            list(request,response);
        }else if(action.equals("update")){
            String userid = request.getParameter("userid");
            System.out.print(userid+"userid");
            //查找要修改的用户资料
            Users user = basedao.findByUserid3(userid);
            //下拉框中的所有部门数据
            List<Dep> depList = basedao.findDepAll3();

            List<degrees> degreesList = basedao.finddegrees2();
            request.setAttribute("degreesList", degreesList);
            request.setAttribute("depList", depList);
            request.setAttribute("user", user);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/userupdate.jsp").forward(request, response);//需改jsp页面
        }else if(action.equals("updateSave")){
            int userid = Integer.parseInt(request.getParameter("userid"));
            int deid = Integer.parseInt(request.getParameter("depid"));
            int degreeid = Integer.parseInt(request.getParameter("degreeid"));
            String jobname = request.getParameter("jobname").trim();
            int managerType = Integer.parseInt(request.getParameter("managerType"));
            String mobile = request.getParameter("mobile").trim();
            String email = request.getParameter("email").trim();
            String qqcode = request.getParameter("qqcode").trim();
            String weixin = request.getParameter("weixin").trim();
            String cardno = request.getParameter("cardno").trim();
            String bankName = request.getParameter("bankName").trim();
            String bankCardNo = request.getParameter("bankCardNo").trim();
            String joinDate = request.getParameter("joinDate").trim();
            String workDate = request.getParameter("workDate").trim();
            String levelDate = request.getParameter("levelDate").trim();
            float baseSalary = Float.parseFloat(request.getParameter("baseSalary").trim());
            float degreeSalary = Float.parseFloat(request.getParameter("degreeSalary").trim());
            String addr = request.getParameter("addr").trim();
            String remark = request.getParameter("remark").trim();
            Users u = new Users();
            u.setUserid(userid);
            u.setDepid(deid);
            u.setDegreeid(degreeid);
            u.setJobname(jobname);
            u.setManagerType(managerType);
            u.setMobile(mobile);
            u.setEmail(email);
            u.setQqcode(qqcode);
            u.setWeixin(weixin);
            u.setCardno(cardno);
            u.setBankName(bankName);
            u.setBankCardNo(bankCardNo);
            u.setJoinDate(joinDate);
            u.setWorkDate(workDate);
            u.setLevelDate(levelDate);
            u.setBaseSalary(baseSalary);
            u.setDegreeSalary(degreeSalary);
            u.setAddr(addr);
            u.setRemark(remark);
            basedao.update3(u);
            list(request,response);
        }else if(action.equals("status")){//更改状态
            String userid = request.getParameter("userid");
            String status = request.getParameter("status");
            if(status.equals("1")){
                status = "0";
            }else{
                status = "1";
            }
            Users u = new Users();
            u.setUserid(Integer.parseInt(userid));
            u.setStatus(Integer.parseInt(status));
            basedao.updateUserStatus3(u);
            list(request,response);
        }else if(action.equals("checkuser")){//判断用户是否重名
            String username = request.getParameter("username");
            boolean b = basedao.checkUser(username);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            if(b){
                out.println("1");
            }else{
                out.println("0");
            }
            out.flush();
            out.close();
        }else if(action.equals("login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String yzm = request.getParameter("yzm");
            String rand = (String) session.getAttribute("rand");
            String rand1 = rand.toLowerCase();
            Users users = new Users();
            users.setUsername(username);
            users.setPassword(password);
            String msg = "";
            users = basedao.login(users);
            if(users==null){
                msg = "用户名不存在";
                request.setAttribute("msg", msg);
                //用户名不存咋
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }else{
                if(!users.getPassword().equals(password) && password!=""){
                    msg = "你输入的密码不正确";
                    request.setAttribute("msg", msg);
                    //密码错误
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }else if(users.getStatus() == 0){
                    msg = "该用户被禁用";
                    request.setAttribute("msg", msg);
                    //密码错误
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }else if((!yzm.equals(rand1) && !yzm.equals(rand))){
                    msg = "你输入的验证码错误,请重新输入";
                    request.setAttribute("msg",msg);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
            }
            int id = users.getUserid();
            //登陆成功
            session.setAttribute("username",username);
            session.setAttribute("id",id);
            session.setAttribute("userinfor",users);
            request.getRequestDispatcher("/index.html").forward(request, response);
        }else if(action.equals("Pwdchange")){
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("newpassword");
            String userid =request.getParameter("userid");
            Users users = basedao.findByUserid3(userid);
            if(oldpassword.equals(users.getPassword())){
                //修改密码
                basedao.changePwd(Integer.parseInt(userid),newpassword);
                request.setAttribute("msg", "密码修改成功,下次登录使用新密码");
                request.getRequestDispatcher("/PwdChange.jsp").forward(request, response);
            }else{
                request.setAttribute("msg", "旧密码错误");
                request.getRequestDispatcher("/PwdChange.jsp").forward(request, response);
                return;
            }
        }
    }

    //分页查询
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        PageObject pager = new PageObject();
        int x=0;
        //读取页面的参数
        String opr = request.getParameter("opr");
        String curPage = request.getParameter("pagecur");
        String value = request.getParameter("val");
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
        int cnt = basedao.findUsersCount3();
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

        List<Users> userList = basedao.findByPageUsers3(pager.getPageSize(), pager.getCurPage());
        //定义request变量，在jsp页面中使用
        request.setAttribute("userList", userList);
        //分页参数
        List<Users> users = basedao.findUserAll3();

        request.setAttribute("users",users);
        request.setAttribute("pager", pager);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/userList.jsp").forward(request, response);//需改jsp页面
    }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
