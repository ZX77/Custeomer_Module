package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同表(contract)
 */
public class contract implements Serializable{
    private int contract_id;    //主键
    private String contract_no; //合同编号
    private int custId;         //客户编号（关联客户表）
    private String contract_time;//合同签订时间
    private String due_time;      //合同到期时间
    private String total_money; //合同金额
    private String deposit;       //合同有效期（年）
    private String pay_type;    //支付类别（按季度支付、按月支付、按半年支付，按年支付）
    private String status;      //合同状态（已签订，已付款，已完成，服务中）
    private int empid;          //业务员（关联用户表）
    private String operator;       //操作员（关联用户表）
    private String oprtime;       //操作时间
    private String customer; //客户

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username; //用户

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getContract_time() {
        return contract_time;
    }

    public void setContract_time(String contract_time) {
        this.contract_time = contract_time;
    }

    public String getDue_time() {
        return due_time;
    }

    public void setDue_time(String due_time) {
        this.due_time = due_time;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOprtime() {
        return oprtime;
    }

    public void setOprtime(String oprtime) {
        this.oprtime = oprtime;
    }
}
