package pidvn.modules.warehouse.iqc_recheck.models;

import pidvn.entities.one.Lots;

import java.util.List;

public class RequestDto {

    private String requestedBy;
    private String type;
    private List<String> lotGroups;
    private String goodsType;

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
