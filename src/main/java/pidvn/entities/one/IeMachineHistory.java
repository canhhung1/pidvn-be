package pidvn.entities.one;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ie_machine_histories")
@EntityListeners(AuditingEntityListener.class)
public class IeMachineHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "stop_time")
    private Date stopTime;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "line")
    private String line;

    @Column(name = "machine_code")
    private String machineCode;

    @Column(name = "incident")
    private String incident;

    @Column(name = "action")
    private String action;

    @Column(name = "reason")
    private String reason;

    @Column(name = "type")
    private String type;

    @Column(name = "user_code")
    private String userCode;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "ie_accept_time")
    private Date ieAcceptTime;

    @Column(name = "ie_accept_user")
    private String ieAcceptUser;

    @Column(name = "ie_repair_time")
    private Date ieRepairTime;

    @Column(name = "ie_repair_user")
    private String ieRepairUser;

    @Column(name = "ma_accept_user")
    private String maAcceptUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public Date getIeAcceptTime() {
        return ieAcceptTime;
    }

    public void setIeAcceptTime(Date ieAcceptTime) {
        this.ieAcceptTime = ieAcceptTime;
    }

    public String getIeAcceptUser() {
        return ieAcceptUser;
    }

    public void setIeAcceptUser(String ieAcceptUser) {
        this.ieAcceptUser = ieAcceptUser;
    }

    public Date getIeRepairTime() {
        return ieRepairTime;
    }

    public void setIeRepairTime(Date ieRepairTime) {
        this.ieRepairTime = ieRepairTime;
    }

    public String getIeRepairUser() {
        return ieRepairUser;
    }

    public void setIeRepairUser(String ieRepairUser) {
        this.ieRepairUser = ieRepairUser;
    }

    public String getMaAcceptUser() {
        return maAcceptUser;
    }

    public void setMaAcceptUser(String maAcceptUser) {
        this.maAcceptUser = maAcceptUser;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
