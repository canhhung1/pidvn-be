package pidvn.modules.hr.uniform.models;

import java.util.Date;

public class UserUniformVo {

    private Integer id;
    private String name;
    private String username;
    private Integer uniformType;
    private String size;
    private Integer qty;
    private Date date;
    private String uniformTypeName;
    private String uploadId;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUniformType() {
        return uniformType;
    }

    public void setUniformType(Integer uniformType) {
        this.uniformType = uniformType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUniformTypeName() {
        return uniformTypeName;
    }

    public void setUniformTypeName(String uniformTypeName) {
        this.uniformTypeName = uniformTypeName;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
