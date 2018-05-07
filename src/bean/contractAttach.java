package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同扫描附件表(contractAttach)
 */
public class contractAttach implements Serializable{
    private int contractAttach_id;  //主键，标识列，自动生成
    private int contract_id;        //合同编号（关联合同表主键）
    private int seq;                //排序号
    private String attachFile;      //附件名称

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    private String uploadTime;        //上传时间

    public int getContractAttach_id() {
        return contractAttach_id;
    }

    public void setContractAttach_id(int contractAttach_id) {
        this.contractAttach_id = contractAttach_id;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAttachURL() {
        return attachURL;
    }

    public void setAttachURL(String attachURL) {
        this.attachURL = attachURL;
    }

    private String user_name;       //操作员编号
    private String attachURL;       //附件地址


}
