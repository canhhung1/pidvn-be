package pidvn.modules.relay.inventory.models;

public class BalanceVo {
    private Integer id;
    private String partNo;
    private String lotNo;
    private String line;
    private Float receiveQty;
    private Float returnQty;
    private Float lineQty;
    private Float systemStock;
    private Float actualStock;
    private Float balance;

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

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Float getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(Float receiveQty) {
        this.receiveQty = receiveQty;
    }

    public Float getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Float returnQty) {
        this.returnQty = returnQty;
    }

    public Float getLineQty() {
        return lineQty;
    }

    public void setLineQty(Float lineQty) {
        this.lineQty = lineQty;
    }

    public Float getSystemStock() {
        return systemStock;
    }

    public void setSystemStock(Float systemStock) {
        this.systemStock = systemStock;
    }

    public Float getActualStock() {
        return actualStock;
    }

    public void setActualStock(Float actualStock) {
        this.actualStock = actualStock;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
