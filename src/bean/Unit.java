package bean;

import java.io.Serializable;

/**
 * Unit(单位表)
 */
public class Unit implements Serializable{
        private  int unitId;    //主键
        private  String unitName;  //用户名称（唯一）

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
