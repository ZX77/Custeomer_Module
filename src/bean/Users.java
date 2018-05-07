package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Users(用户表)
 */
public class Users implements Serializable{
    private  int userid;    //主键
    private  String username;//用户名称（唯一）
    private  String password;//密码
    private  int depid;      //部门编号（关联部门表主键）
    private int degreeid;   //岗位编号（关联岗位表主键）
    private String jobname;  //职务
    private  int managerType;//管理员类别（0系统管理员，1部门经理，2员工）
    private  String mobile;   //联系电话
    private  String email;     //邮箱
    private  String qqcode;     //QQ
    private  String weixin;     //微信号
    private  String cardno;     //身份证号码
    private  String bankName;   //开户银行
    private  String bankCardNo;//银行账号
    private  String joinDate;     //入职日期
    private  String workDate;     //试用转正日期
    private  String levelDate;    //离职日期
    private  float baseSalary;  //基本工资
    private  float degreeSalary;//岗位工资
    private  String  addr;        //联系地址
    private  int status;          //用户状态（1=启用，0=禁用）
    private  String remark;        //说明

    //分页时使用
    private String depname;
    private String degreename;

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public int getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(int degreeid) {
        this.degreeid = degreeid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public int getManagerType() {
        return managerType;
    }

    public void setManagerType(int managerType) {
        this.managerType = managerType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqcode() {
        return qqcode;
    }

    public void setQqcode(String qqcode) {
        this.qqcode = qqcode;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getLevelDate() {
        return levelDate;
    }

    public void setLevelDate(String levelDate) {
        this.levelDate = levelDate;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public float getDegreeSalary() {
        return degreeSalary;
    }

    public void setDegreeSalary(float degreeSalary) {
        this.degreeSalary = degreeSalary;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
