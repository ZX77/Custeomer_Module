package bean;

import sun.dc.pr.PRError;

import java.io.Serializable;
import java.util.Date;

/**
 * 派工单(jobRecord)
 */
public class jobRecord implements Serializable{

    private int jobId; //派工编号
    private String orderId;     //订单号
    private String jobDate;       //日期
    private String prodName;   // 商品信息（关联商品表）
    private int custid;         //客户名称（关联客户表）
    private String jobContent; // 派工内容
    private String callback;    //派工完成情况
    private String userid;      //员工信息
    private String custEval;   // 客户评价
    private String custSign;    //客户签名(已签，未签)
    private String startTime;    // 开工时间
    private String endTime;      // 结束时间
    private int workDay;       //人工天数
    private float busMoney;    // 交通费
    private float attachMoney; //补助费
    private String prodinfor; //商品名称
    private String custname; //客户名称

    public String getProdinfor() {
        return prodinfor;
    }

    public void setProdinfor(String prodinfor) {
        this.prodinfor = prodinfor;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username; //员工名称

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCustEval() {
        return custEval;
    }

    public void setCustEval(String custEval) {
        this.custEval = custEval;
    }

    public String getCustSign() {
        return custSign;
    }

    public void setCustSign(String custSign) {
        this.custSign = custSign;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public float getBusMoney() {
        return busMoney;
    }

    public void setBusMoney(float busMoney) {
        this.busMoney = busMoney;
    }

    public float getAttachMoney() {
        return attachMoney;
    }

    public void setAttachMoney(float attachMoney) {
        this.attachMoney = attachMoney;
    }
}
