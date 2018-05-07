package bean;

import java.io.Serializable;

/**
 * Dep(部门表)
 */
public class Dep implements Serializable{
    private  int depid;      //部门编号
    private  String depname;  //部门名称
    private  String chairman;   //部门负责人
    private String description; //部门描述

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }






}
