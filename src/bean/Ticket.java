package bean;

import sun.dc.pr.PRError;

import java.io.Serializable;
import java.util.Date;

/**
 * Ticket(开票信息表)
 */
public class Ticket implements Serializable{
    private int id;         //主键
    private String ticketDate; //开票日期
    private String orderid; //订单号
    private int custid;     //客户编号（关联客户表）
    private float ticketMoney; //开票金额
    private String ticketComp;  //出票公司
    private String username;    //用户名称，（session用户名）
    private String oprtime;       //操作时间
    private String remark;      //备注

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    private String custname; //客户名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public float getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(float ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public String getTicketComp() {
        return ticketComp;
    }

    public void setTicketComp(String ticketComp) {
        this.ticketComp = ticketComp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOprtime() {
        return oprtime;
    }

    public void setOprtime(String oprtime) {
        this.oprtime = oprtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
