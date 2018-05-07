package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表(Orders)
 */
public class Orders implements Serializable{
    private int orderId;    //订单编号
    private int custid;     //客户编号（关联客户资料表）
    private int userid;     //业务员（关联用户表id）
    private String orderType;//订单类别（采购入库，销售出库）
    private String orderStatus;//订单状态（已出货，已收款，实施中）
    private String process;     //进度
    private float totalMoney;   //订单金额
    private String oprtime;        //开票时间
    private String operator;        //开票人（关联用户表id）
    private String remark;        //描述
    private String username; //业务员
    private String customername; //客户名称

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOprtime() {
        return oprtime;
    }

    public void setOprtime(String oprtime) {
        this.oprtime = oprtime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
