package pidvn.modules.ie.drawing_control.models;

import java.util.Date;

public class ProjectProgressDto {

    private Integer id;
    private Integer projectProgressId;
    private String projectProgressName;
    private Integer progress;
    private Date startPlan;
    private Date endPlan;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectProgressId() {
        return projectProgressId;
    }

    public void setProjectProgressId(Integer projectProgressId) {
        this.projectProgressId = projectProgressId;
    }

    public String getProjectProgressName() {
        return projectProgressName;
    }

    public void setProjectProgressName(String projectProgressName) {
        this.projectProgressName = projectProgressName;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Date getStartPlan() {
        return startPlan;
    }

    public void setStartPlan(Date startPlan) {
        this.startPlan = startPlan;
    }

    public Date getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(Date endPlan) {
        this.endPlan = endPlan;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
