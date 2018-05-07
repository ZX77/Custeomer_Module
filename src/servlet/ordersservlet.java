package servlet;

import bean.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.ContextUtils;
import db.BaseDAO;
import javafx.animation.Animation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ordersservlet extends HttpServlet {
    //数据库操作类
    BaseDAO basedao = new BaseDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取action参数的值
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("userinfor");
        if(action==null){
            action="list";
        }
        if(action.equals("init")){
            List<CustomerInfo> customerInfoList = basedao.findcustomer7();//需改baseDAO
            request.setAttribute("customerInfoList", customerInfoList);
            List<Users> usersList = basedao.findUserAll3();//需改baseDAO
            request.setAttribute("usersList", usersList);
            List<Product> productList = basedao.findproduct6();
            request.setAttribute("productList",productList);
            List<Orders> ordersList = basedao.findorders10();
            request.setAttribute("ordersList",ordersList);
            List<Unit> unitList = basedao.findunit4();
            request.setAttribute("unitList",unitList);
            request.getRequestDispatcher("/addOrders.jsp").forward(request, response);//需改jsp页面
            //新增
        }else if(action.equals("add")){
            int custid = Integer.parseInt(request.getParameter("custid"));
            int userid = Integer.parseInt(request.getParameter("userid"));
            String oprtime = request.getParameter("oprtime").trim();
            String remark = request.getParameter("remark").trim();

            String orderId = request.getParameter("orderId").trim();
            String prodid = request.getParameter("prodid").trim();
            Float saleMoney = Float.parseFloat(request.getParameter("saleMoney").trim());
            int UnitId = Integer.parseInt(request.getParameter("unitId"));
            String regPerson = request.getParameter("regPerson").trim();
            String regPassword = request.getParameter("regPassword").trim();
            int prodCount = Integer.parseInt(request.getParameter("prodCount"));

            OrderDetail d = new OrderDetail();
            d.setOrderId(orderId);
            d.setProdid(prodid);
            d.setStatus("1");
            d.setSaleMoney(saleMoney);
            d.setUnitId(UnitId);
            d.setRegPerson(regPerson);
            d.setRegPassword(regPassword);
            d.setServicePeriod("");
            d.setExpireDate("");
            d.setProdCount(prodCount);
            d.setTotalMoney(saleMoney*prodCount);
            d.setOprtime(ContextUtils.dateToStrShort(new Date()));
            d.setOperator(users.getUsername());
            d.setRemark(remark);
            Orders o = new Orders();
            o.setOrderId(Integer.parseInt(orderId));
            o.setCustid(custid);
            o.setUserid(userid);
            o.setOrderType("销售入库");
            o.setOrderStatus("已出货");
            o.setProcess("已开单");
            o.setTotalMoney(0);
            o.setOprtime(ContextUtils.dateToStrShort(new Date()));
            o.setOperator(users.getUsername());
            o.setRemark(remark);
            //判断订单是否存在
            boolean b = basedao.orderexit(Integer.parseInt(orderId));
            if(!b){
                basedao.save10(o);
            }
            basedao.save11(d);
            //修改金额
            int money = basedao.ordertotalmoney(Integer.parseInt(orderId));
            basedao.updateOrder(money,orderId);

            //修改库存数量
            Product product = basedao.findByStudId6(prodid);
            basedao.updateprodcount(product,prodCount);

            List<OrderDetail> detailList = basedao.findByPage11(1000,1,Integer.parseInt(orderId));
            JSONObject json = new JSONObject();
            json.put("ord", orderId);
            JSONArray arr = new JSONArray();
            for (OrderDetail ds: detailList) {
                arr.add(ds);
            }
            json.put("detailList", arr);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(json.toJSONString());
            out.flush();
            out.close();
        }else if(action.equals("list")){//列表
            list(request,response);
        }else if(action.equals("del")){//删除
            String orderId = request.getParameter("orderId");
            //修改商品库存
            //获取订单明细的商品id
            List<OrderDetail> orderDetailList = basedao.finddetaList(orderId);
            for (OrderDetail orderDetail:orderDetailList){
                Product product = basedao.findByStudId6(orderDetail.getProdid());
                Orders orders = basedao.findByStudId10(orderDetail.getOrderId());
                //获取删除订单明细中相同订单的总数量
                int count = basedao.detailcount(orderId,orderDetail.getDetailId());
                System.out.println(count+"..................");
                //修改数量
                basedao.prodcountup(product,count,orders.getOrderType());
            }
            //删除订单
            basedao.del10(orderId);
            //删除订单明细
            basedao.updatedetail(orderId);
            list(request,response);
        }else if(action.equals("update")){
            String orderId = request.getParameter("orderId");
            Orders orders = basedao.findByStudId10(orderId);
            request.setAttribute("orders", orders);
            List<CustomerInfo> customerInfoList = basedao.findcustomer7();//需改baseDAO
            request.setAttribute("customerInfoList", customerInfoList);
            List<Users> usersList = basedao.findUserAll3();//需改baseDAO
            request.setAttribute("usersList", usersList);
            //转向到列表页面,这个语句相当于jsp:forward动作元素
            request.getRequestDispatcher("/ordersUpdate.jsp").forward(request, response);
        }else if(action.equals("updateSave")){
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String custid = request.getParameter("custid");
            String userid = request.getParameter("userid").trim();
            String orderType = request.getParameter("orderType").trim();
            String orderStatus = request.getParameter("orderStatus").trim();
            String process = request.getParameter("process").trim();
            Float totalMoney = Float.parseFloat(request.getParameter("totalMoney").trim());
            String oprtime = request.getParameter("oprtime").trim();
            String operator = request.getParameter("operator").trim();
            String remark = request.getParameter("remark").trim();
            Orders d = new Orders();
            d.setOrderId(orderId);
            d.setCustid(Integer.parseInt(custid));
            d.setUserid(Integer.parseInt(userid));
            d.setOrderType(orderType);
            d.setOrderStatus(orderStatus);
            d.setProcess(process);
            d.setTotalMoney(totalMoney);
            d.setOprtime(oprtime);
            d.setOperator(operator);
            d.setRemark(remark);
            basedao.update10(d);
            list(request,response);
        }else if(action.equals("orderId")){
            PrintWriter out = response.getWriter();
            //获取商品详细信息
            String prodid = request.getParameter("prodid");
            Product prod = basedao.findByStudId6(prodid);
            JSONObject json = new JSONObject();
            json.put("price", prod.getSalePrice());
            json.put("unitId", prod.getProdUnit());
            json.put("orderId",basedao.ordersrand());
            out.println(json.toJSONString());
            out.flush();
            out.close();
        }else if(action.equals("price")){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String prodid = request.getParameter("prodid");
            Product prod = basedao.findByStudId6(prodid);

            JSONObject json = new JSONObject();
            json.put("price", prod.getSalePrice());
            json.put("unitId", prod.getProdUnit());
            out.println(json.toJSONString());
            out.flush();
            out.close();
        }else if(action.equals("status")){
            String msg = null;
            String orderId = request.getParameter("orderId");
            String statu = request.getParameter("statu");
            Orders orders = basedao.findByStudId10(orderId);
            System.out.println(orders.getOrderStatus()+"-----"+statu);
            if(orders.getOrderStatus().equals("已收款")){
                msg = "你的状态已是收款,无法更换其他状态";
            }else if(orders.getOrderStatus().equals(statu)){
                msg = "订单状态相同,无须进行修改";
            }else if((orders.getOrderStatus().equals("已出货") && statu.equals("已收款")) || (orders.getOrderStatus().equals("已收款") && statu.equals("已出货"))){
                msg = "订单不能跳跃修改";
            }else{
                basedao.updatestatus(orderId,statu);
                Orders orders1 = basedao.findByStudId10(orderId);
                if(orders1.getOrderStatus().equals("已收款")){
                    List<OrderDetail> orderDetailList = basedao.finddetaList(orderId);
                    for(OrderDetail orderDetail:orderDetailList){
                        //新增一笔收款管理
                        finance d = new finance();
                        d.setOrderId(orderId);
                        d.setProdid(Integer.parseInt(orderDetail.getProdid()));
                        d.setPaidtypeid("4");
                        d.setRemainMoney(0);
                        d.setPaidMoney(orderDetail.getTotalMoney());
                        d.setOrderMoney(orders1.getTotalMoney());
                        d.setPaidPerson(orders1.getCustomername());
                        d.setInbank("中国银行");
                        d.setBankAccount("17990433");
                        d.setOutbank("中国银行");
                        d.setWarrant("x"+(int)(Math.random()*100000));
                        d.setPaidTime(ContextUtils.dateToStrShort(new Date()));
                        d.setPaidinTime("");
                        d.setInvalid("有效");
                        d.setUsername(users.getUsername());
                        d.setOprtime(ContextUtils.dateToStrShort(new Date()));
                        d.setOprType("收款");
                        basedao.save16(d);
                    }
                    //新增一笔开票信息
                    Ticket d = new Ticket();
                    d.setTicketDate(ContextUtils.dateToStrShort(new Date()));
                    d.setOrderid(orderId);
                    d.setCustid(orders1.getCustid());
                    d.setTicketMoney(orders1.getTotalMoney());
                    d.setTicketComp("上海公司");
                    d.setUsername(users.getUsername());
                    d.setOprtime(ContextUtils.dateToStrShort(new Date()));
                    d.setRemark("已核实开票信息");
                    basedao.save17(d);
                }
            }
            request.setAttribute("msg",msg);
            list(request,response);
        }else if(action.equals("GoProd")){  //采购入库
            List<CustomerInfo> customerInfoList = basedao.findcustomer7();//需改baseDAO
            request.setAttribute("customerInfoList", customerInfoList);
            List<Users> usersList = basedao.findUserAll3();//需改baseDAO
            request.setAttribute("usersList", usersList);
            List<Product> productList = basedao.findproduct6();
            request.setAttribute("productList",productList);
            List<Orders> ordersList = basedao.findorders10();
            request.setAttribute("ordersList",ordersList);
            List<Unit> unitList = basedao.findunit4();
            request.setAttribute("unitList",unitList);
            request.getRequestDispatcher("/warehousing.jsp").forward(request, response);//需改jsp页面
        }else if(action.equals("warehousingadd")){
            int custid = Integer.parseInt(request.getParameter("custid"));
            int userid = Integer.parseInt(request.getParameter("userid"));
            String oprtime = request.getParameter("oprtime").trim();
            String remark = request.getParameter("remark").trim();

            String orderId = request.getParameter("orderId").trim();
            String prodid = request.getParameter("prodid").trim();
            Float saleMoney = Float.parseFloat(request.getParameter("saleMoney").trim());
            int UnitId = Integer.parseInt(request.getParameter("unitId"));
            String regPerson = request.getParameter("regPerson").trim();
            String regPassword = request.getParameter("regPassword").trim();
            int prodCount = Integer.parseInt(request.getParameter("prodCount"));

            OrderDetail d = new OrderDetail();
            d.setOrderId(orderId);
            d.setProdid(prodid);
            d.setStatus("1");
            d.setSaleMoney(saleMoney);
            d.setUnitId(UnitId);
            d.setRegPerson(regPerson);
            d.setRegPassword(regPassword);
            d.setServicePeriod("");
            d.setExpireDate("");
            d.setProdCount(prodCount);
            d.setTotalMoney(saleMoney*prodCount);
            d.setOprtime(ContextUtils.dateToStrShort(new Date()));
            d.setOperator(users.getUsername());
            d.setRemark(remark);
            Orders o = new Orders();
            o.setOrderId(Integer.parseInt(orderId));
            o.setCustid(custid);
            o.setUserid(userid);
            o.setOrderType("采购入库");
            o.setOrderStatus("已出货");
            o.setProcess("已开单");
            o.setTotalMoney(0);
            o.setOprtime(oprtime);
            o.setOperator(users.getUsername());
            o.setRemark(remark);
            //判断订单是否存在
            boolean b = basedao.orderexit(Integer.parseInt(orderId));
            if(!b){
                basedao.save10(o);
            }
            basedao.save11(d);
            //修改金额
            int money = basedao.ordertotalmoney(Integer.parseInt(orderId));
            basedao.updateOrder(money,orderId);

            //修改库存数量
            Product product = basedao.findByStudId6(prodid);
            basedao.updateprodcount(product,(-prodCount));
            List<OrderDetail> detailList = basedao.findByPage11(1000,1,Integer.parseInt(orderId));
            JSONObject json = new JSONObject();
            json.put("ord", orderId);
            JSONArray arr = new JSONArray();
            for (OrderDetail ds: detailList) {
                arr.add(ds);
            }
            json.put("detailList", arr);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(json.toJSONString());
            out.flush();
            out.close();
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
        List<Orders> ordersList = null;
        String custid = null;
        if(param==null){
            ordersList= basedao.findByPage10(pager.getPageSize(), pager.getCurPage());
        }else if(param.equals("select")){
            custid = request.getParameter("selected");
            if(custid.equals("0")){
                ordersList= basedao.findByPage10(pager.getPageSize(), pager.getCurPage());
            }else{
                ordersList= basedao.findByPage10(1000,1,Integer.parseInt(custid));
            }
        }
        //定义客户Id
        request.setAttribute("custid",custid);
        List<CustomerInfo> customerInfoList = basedao.findcustomer7();
        request.setAttribute("customerInfoList",customerInfoList);
        //查询列表
        //定义request变量，在jsp页面中使用
        request.setAttribute("ordersList", ordersList);
        List<Orders>  orders = basedao.findorders10();
        //分页参数
        request.setAttribute("pager", pager);

        request.setAttribute("orders",orders);
        //转向到列表页面,这个语句相当于jsp:forward动作元素find
        request.getRequestDispatcher("/ordersList.jsp").forward(request, response);//需改jsp页面
    }
}
