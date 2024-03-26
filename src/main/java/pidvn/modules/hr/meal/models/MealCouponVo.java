package pidvn.modules.hr.meal.models;

public class MealCouponVo {

    private String username;
    private String profileName;
    private Integer actCoupon;
    private Integer tsCoupon;
    private Integer actSubCoupon;
    private Integer tsSubCoupon;
    private Integer balanceCoupon;
    private Integer balanceSubCoupon;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Integer getActCoupon() {
        return actCoupon;
    }

    public void setActCoupon(Integer actCoupon) {
        this.actCoupon = actCoupon;
    }

    public Integer getTsCoupon() {
        return tsCoupon;
    }

    public void setTsCoupon(Integer tsCoupon) {
        this.tsCoupon = tsCoupon;
    }

    public Integer getActSubCoupon() {
        return actSubCoupon;
    }

    public void setActSubCoupon(Integer actSubCoupon) {
        this.actSubCoupon = actSubCoupon;
    }

    public Integer getTsSubCoupon() {
        return tsSubCoupon;
    }

    public void setTsSubCoupon(Integer tsSubCoupon) {
        this.tsSubCoupon = tsSubCoupon;
    }

    public Integer getBalanceCoupon() {
        return balanceCoupon;
    }

    public void setBalanceCoupon(Integer balanceCoupon) {
        this.balanceCoupon = balanceCoupon;
    }

    public Integer getBalanceSubCoupon() {
        return balanceSubCoupon;
    }

    public void setBalanceSubCoupon(Integer balanceSubCoupon) {
        this.balanceSubCoupon = balanceSubCoupon;
    }
}
