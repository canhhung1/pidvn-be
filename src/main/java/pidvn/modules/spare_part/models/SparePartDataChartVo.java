package pidvn.modules.spare_part.models;

import java.util.Date;

public class SparePartDataChartVo {

    private String galileoName;
    private Integer qty;
    private Date date;
    private String machine;
    private String line;
    private Float totalAmount;

    public String getGalileoName() {
        return galileoName;
    }

    public void setGalileoName(String galileoName) {
        this.galileoName = galileoName;
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

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
