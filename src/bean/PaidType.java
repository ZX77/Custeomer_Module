package bean;

import java.io.Serializable;

/**
 * 收款方式(PaidType)
 */
public class PaidType implements Serializable{
    private  int paidtypeid;    //编号
    private  String paidtypename;//名称（POS刷卡，微信，支付宝，现金，转账）

    public int getPaidtypeid() {
        return paidtypeid;
    }

    public void setPaidtypeid(int paidtypeid) {
        this.paidtypeid = paidtypeid;
    }

    public String getPaidtypename() {
        return paidtypename;
    }

    public void setPaidtypename(String paidtypename) {
        this.paidtypename = paidtypename;
    }
}
