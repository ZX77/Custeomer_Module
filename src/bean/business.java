package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 商务洽谈表(business)
 */
public class business implements Serializable{

    private int businessId; //商业洽谈编号
    private String busDate; //日期
    private String prodName;//商品信息
    private String chatContent;//洽谈内容
    private String chatResult;//洽谈情况
    private int custid;//客户名称（关联客户表主键）
    private String custContact;//客户联系人
    private String phone;//客户电话
    private int userid;//员工信息(关联用户表主键)
    private String module;//报价模块
    private String moduleState;//报价情况
    private float moduleMoney;//报价金额
    private String remark;//备注
    private String custname; //客户信息

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

    private String username; //员工信息

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusDate() {
        return busDate;
    }

    public void setBusDate(String busDate) {
        this.busDate = busDate;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getChatResult() {
        return chatResult;
    }

    public void setChatResult(String chatResult) {
        this.chatResult = chatResult;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getCustContact() {
        return custContact;
    }

    public void setCustContact(String custContact) {
        this.custContact = custContact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModuleState() {
        return moduleState;
    }

    public void setModuleState(String moduleState) {
        this.moduleState = moduleState;
    }

    public float getModuleMoney() {
        return moduleMoney;
    }

    public void setModuleMoney(float moduleMoney) {
        this.moduleMoney = moduleMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
