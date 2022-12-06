package pidvn.modules.is.device_management.models;

public class DeviceSearchVo {
    private String model;
    private String mngCode;
    private String serial;
    private String imei;
    private Integer deviceType;
    private Integer status;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMngCode() {
        return mngCode;
    }

    public void setMngCode(String mngCode) {
        this.mngCode = mngCode;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
