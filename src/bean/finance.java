package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 收款管理(finance)
 */
public class finance implements Serializable{
    private int financeId;  //编号
    private String orderId; //订单号（关联订单表）
    private int prodid;     //产品名称
    private String paidtypeid;  //收款方式类别(关联付款方式表）
    private float remainMoney;  //应收金额
    private float paidMoney;    //收款金额
    private float orderMoney;   //订单金额
    private String paidPerson;  //交款人
    private String inbank;      //入账银行
    private String bankAccount; //入账账号
    private String outbank;     //出账银行
    private String warrant;     //相关凭证号
    private String paidTime;      //交款时间
    private String paidinTime;    //到账日期
    private String invalid;     //是否有效（有效，作废）
    private String username;    //操作人（session取值，登录账号）
    private String oprtime;       //录入时间
    private String oprType;     //操作类别（收款，付款）

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    private String prodname; //产品名称

    public String getPaidtypename() {
        return paidtypename;
    }

    public void setPaidtypename(String paidtypename) {
        this.paidtypename = paidtypename;
    }

    private String paidtypename; //收款方式

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getPaidtypeid() {
        return paidtypeid;
    }

    public void setPaidtypeid(String paidtypeid) {
        this.paidtypeid = paidtypeid;
    }

    public float getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(float remainMoney) {
        this.remainMoney = remainMoney;
    }

    public float getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(float paidMoney) {
        this.paidMoney = paidMoney;
    }

    public float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getPaidPerson() {
        return paidPerson;
    }

    public void setPaidPerson(String paidPerson) {
        this.paidPerson = paidPerson;
    }

    public String getInbank() {
        return inbank;
    }

    public void setInbank(String inbank) {
        this.inbank = inbank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOutbank() {
        return outbank;
    }

    public void setOutbank(String outbank) {
        this.outbank = outbank;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public String getPaidinTime() {
        return paidinTime;
    }

    public void setPaidinTime(String paidinTime) {
        this.paidinTime = paidinTime;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
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

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
    }



}
