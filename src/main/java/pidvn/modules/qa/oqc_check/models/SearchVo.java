package pidvn.modules.qa.oqc_check.models;

import java.util.Date;
import java.util.List;

public class SearchVo {
    private String url;

    private String reqNo;

    private List<Integer> requestStatusList;

    private List<Date> requestDateRange;

    private String isAudit;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public List<Integer> getRequestStatusList() {
        return requestStatusList;
    }

    public void setRequestStatusList(List<Integer> requestStatusList) {
        this.requestStatusList = requestStatusList;
    }

    public List<Date> getRequestDateRange() {
        return requestDateRange;
    }

    public void setRequestDateRange(List<Date> requestDateRange) {
        this.requestDateRange = requestDateRange;
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }
}
