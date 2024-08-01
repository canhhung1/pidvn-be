package pidvn.modules.ie.drawing_control.models;

import java.util.Date;

public class SearchDto {

    private String url;
    private Date date;
    private Integer projectTypeId;
    private Integer productTypeId;
    private String personInChargeId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getPersonInChargeId() {
        return personInChargeId;
    }

    public void setPersonInChargeId(String personInChargeId) {
        this.personInChargeId = personInChargeId;
    }
}
