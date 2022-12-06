package pidvn.modules.personal.uniform.models;

import java.util.Date;

public class UserUniformVo {

    private Integer id;
    private String username;
    private Integer uniformType;
    private String size;
    private Integer qty;
    private Date date;
    private String uniformTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}
