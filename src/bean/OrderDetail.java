package bean;

import sun.dc.pr.PRError;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细表(OrderDetail)
 */
public class OrderDetail implements Serializable{
    private int DetailId;   //订单明细编号
    private String orderId; //订单编号
    private String prodid;  //商品编号
    private String status;  //销售类别（销售，赠送，配套）
    private float saleMoney; //销售金额
    private int UnitId;       //单位
    private String regPerson;   //注册联系人
    private String regPassword; //注册密码
    private String servicePeriod; //服务期限
    private String expireDate;    //服务到期日期
    private int prodCount;        //销售数量
    private float totalMoney;     //总金额
    private String oprtime;          //开票时间
    private String operator;          //开票人
    private String remark;         //描述

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    private String ordername; //订单名

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    private String prodname; //商品名称


    public int getDetailId() {
        return DetailId;
    }

    public void setDetailId(int detailId) {
        DetailId = detailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(float saleMoney) {
        this.saleMoney = saleMoney;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public String getRegPerson() {
        return regPerson;
    }

    public void setRegPerson(String regPerson) {
        this.regPerson = regPerson;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getServicePeriod() {
        return servicePeriod;
    }

    public void setServicePeriod(String servicePeriod) {
        this.servicePeriod = servicePeriod;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getProdCount() {
        return prodCount;
    }

    public void setProdCount(int prodCount) {
        this.prodCount = prodCount;
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
