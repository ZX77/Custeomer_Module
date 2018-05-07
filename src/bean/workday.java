package bean;

import java.io.Serializable;
import java.util.Date;

/**
 *    workday工作表
 */
public class workday implements Serializable{
    private int id;     //主键
    private String weekDate; //日期
    private String workContent;//周工作内容
    private String workReview;  //周工作总结
    private String question;    //存在问题
    private String warning;     //急需解决问题
    private String weekPlan;    //下周计划
    private String username;    //操作人
    private String oprtime;       //操作时间
    private String remark;      //备注


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkReview() {
        return workReview;
    }

    public void setWorkReview(String workReview) {
        this.workReview = workReview;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(String weekPlan) {
        this.weekPlan = weekPlan;
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
