package pidvn.modules.hr.meal.models;

import java.util.Date;

public class MealRecordVo {

    private Integer rowNum;
    private String codeEmp;
    private String profileName;
    private Date timeLog;
    private String machineCode;
    private Float amount;
    private String canteenCode;
    private String canteenName;
    private String lineCode;
    private String lineName;

    private String orgStructureName;

    private String ticketType;

    private Integer total;

    private Date date;

    private Integer ticketTimeSheet;
    private Integer ticketActual;


    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Date getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(Date timeLog) {
        this.timeLog = timeLog;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public void setCanteenCode(String canteenCode) {
        this.canteenCode = canteenCode;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getOrgStructureName() {
        return orgStructureName;
    }

    public void setOrgStructureName(String orgStructureName) {
        this.orgStructureName = orgStructureName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTicketTimeSheet() {
        return ticketTimeSheet;
    }

    public void setTicketTimeSheet(Integer ticketTimeSheet) {
        this.ticketTimeSheet = ticketTimeSheet;
    }

    public Integer getTicketActual() {
        return ticketActual;
    }

    public void setTicketActual(Integer ticketActual) {
        this.ticketActual = ticketActual;
    }
}
