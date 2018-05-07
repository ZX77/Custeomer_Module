package bean;

import sun.dc.pr.PRError;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户资料(CustomerInfo)
 */
public class CustomerInfo implements Serializable{
    private  int custId;    //客户编号
    private String custname;//客户名称
    private String custtype;//行业
    private String bankAccount;//银行账号
    private String bankName;    //开户银行
    private String Contact;     //联系人
    private String Phone;       //电话
    private String TicketName;    //开票名称
    private String TicketAddr;  //开票地址
    private String TicketTel;   //开票电话
    private String TaxNo;       //纳税登记号
    private String custState;   //客户状态（新客户，成交客户）
    private String username;         //业务员（关联用户表）
    private String source;      //信息来源

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    private String users; //业务员

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTicketName() {
        return TicketName;
    }

    public void setTicketName(String ticketName) {
        TicketName = ticketName;
    }

    public String getTicketAddr() {
        return TicketAddr;
    }

    public void setTicketAddr(String ticketAddr) {
        TicketAddr = ticketAddr;
    }

    public String getTicketTel() {
        return TicketTel;
    }

    public void setTicketTel(String ticketTel) {
        TicketTel = ticketTel;
    }

    public String getTaxNo() {
        return TaxNo;
    }

    public void setTaxNo(String taxNo) {
        TaxNo = taxNo;
    }

    public String getCustState() {
        return custState;
    }

    public void setCustState(String custState) {
        this.custState = custState;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
