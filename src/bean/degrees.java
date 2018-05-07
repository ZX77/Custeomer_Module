package bean;

import java.io.Serializable;

/**
 * degrees(岗位表)
 */
public class degrees implements Serializable{
    private  int degreeid;      //主键
    private  String degreename; //用户名

    public int getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(int degreeid) {
        this.degreeid = degreeid;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }
}
