package pidvn.modules.relay.material_control.models;

import java.util.Date;

public class MaterialVo {
    private Integer id;
    private String partNo;
    private String model;
    private String lotGroup;
    private String lotNo;
    private String recordType;
    private String recordTypeName;
    private String slipNo;
    private String reqNo;
    private Float qty;
    private Float originQty;
    private Float receiveQty;
    private Float lineQty;
    private Float actualQty;
    private Date date;
    private String line;
    private String shift;
    private Integer processId;
    private String processName;
    private String qaCard;
    private String qrCode;
    private String sender;
    private String receiver;
    private String whUserCode;
    private String username;
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLotGroup() {
        return lotGroup;
    }

    public void setLotGroup(String lotGroup) {
        this.lotGroup = lotGroup;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordTypeName() {
        return recordTypeName;
    }

    public void setRecordTypeName(String recordTypeName) {
        this.recordTypeName = recordTypeName;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getOriginQty() {
        return originQty;
    }

    public void setOriginQty(Float originQty) {
        this.originQty = originQty;
    }

    public Float getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(Float receiveQty) {
        this.receiveQty = receiveQty;
    }

    public Float getLineQty() {
        return lineQty;
    }

    public void setLineQty(Float lineQty) {
        this.lineQty = lineQty;
    }

    public Float getActualQty() {
        return actualQty;
    }

    public void setActualQty(Float actualQty) {
        this.actualQty = actualQty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getWhUserCode() {
        return whUserCode;
    }

    public void setWhUserCode(String whUserCode) {
        this.whUserCode = whUserCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
