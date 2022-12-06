package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pur_wh_records")
@EntityListeners(AuditingEntityListener.class)
public class PurWhRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "record_type")
    private String recordType;

    @Column(name = "qty")
    private Float qty;

    @Column(name = "model")
    private String model;

    @Column(name = "date")
    private Date date;

    @Column(name = "parent")
    private String parent;

    @Column(name = "remark")
    private String remark;

    @Column(name = "pih_user_code")
    private String pihUserCode;

    @Column(name = "wh_user_code")
    private String whUserCode;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "slip_no")
    private String slipNo;

    @Column(name = "serial")
    private Integer serial;

    @Column(name = "type")
    private String type;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "outer_label")
    private String outerLabel;

    @Column(name = "flag")
    private String flag;

    @Column(name = "invoice")
    private String invoice;

    @Column(name = "po")
    private String po;

    @Column(name = "spool")
    private String spool;

    @Column(name = "req_no")
    private String reqNo;

    @Column(name = "io_flag")
    private String ioFlag;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "type_slip_no")
    private String typeSlipNo;

    @Column(name = "hidden_delete")
    private String hiddenDelete;

    @Column(name = "slip_in_month")
    private String slipInMonth;

    @Column(name = "serial_in_month")
    private Integer serialInMonth;

    @Column(name = "re_export_no")
    private Integer reExportNo;

    @Column(name = "qa_card")
    private String qaCard;

    @Column(name = "line")
    private String line;

    @Column(name = "shift")
    private String shift;

    @Column(name = "wip")
    private String wip;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPihUserCode() {
        return pihUserCode;
    }

    public void setPihUserCode(String pihUserCode) {
        this.pihUserCode = pihUserCode;
    }

    public String getWhUserCode() {
        return whUserCode;
    }

    public void setWhUserCode(String whUserCode) {
        this.whUserCode = whUserCode;
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

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getOuterLabel() {
        return outerLabel;
    }

    public void setOuterLabel(String outerLabel) {
        this.outerLabel = outerLabel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getSpool() {
        return spool;
    }

    public void setSpool(String spool) {
        this.spool = spool;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getIoFlag() {
        return ioFlag;
    }

    public void setIoFlag(String ioFlag) {
        this.ioFlag = ioFlag;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getTypeSlipNo() {
        return typeSlipNo;
    }

    public void setTypeSlipNo(String typeSlipNo) {
        this.typeSlipNo = typeSlipNo;
    }

    public String getHiddenDelete() {
        return hiddenDelete;
    }

    public void setHiddenDelete(String hiddenDelete) {
        this.hiddenDelete = hiddenDelete;
    }

    public String getSlipInMonth() {
        return slipInMonth;
    }

    public void setSlipInMonth(String slipInMonth) {
        this.slipInMonth = slipInMonth;
    }

    public Integer getSerialInMonth() {
        return serialInMonth;
    }

    public void setSerialInMonth(Integer serialInMonth) {
        this.serialInMonth = serialInMonth;
    }

    public Integer getReExportNo() {
        return reExportNo;
    }

    public void setReExportNo(Integer reExportNo) {
        this.reExportNo = reExportNo;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
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

    public String getWip() {
        return wip;
    }

    public void setWip(String wip) {
        this.wip = wip;
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

    @Override
    public String toString() {
        return "PurWhRecords{" +
                "id=" + id +
                ", lotNo='" + lotNo + '\'' +
                ", recordType='" + recordType + '\'' +
                ", qty=" + qty +
                ", parent='" + parent + '\'' +
                '}';
    }
}
