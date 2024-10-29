package pidvn.modules.iqc.models;

import java.util.Date;
import java.util.List;

public class SearchDto {
    private List<Date> dateRange;
    private String requestNo;
    private String invoice;
    private String supplier;
    private String type;
    private List<String> lotGroups;

    public List<Date> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<Date> dateRange) {
        this.dateRange = dateRange;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getLotGroups() {
        return lotGroups;
    }

    public void setLotGroups(List<String> lotGroups) {
        this.lotGroups = lotGroups;
    }
}
