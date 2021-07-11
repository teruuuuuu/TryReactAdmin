package jp.co.teruuu.ec_modeling.app.domain.order.model;

public class UsedCoupon {
  public static final long serialVersionUID = 19;
  int couponId;
  int discount;

  public UsedCoupon(int couponId, int discount) {
    this.couponId = couponId;
    this.discount = discount;
  }

  public int getCouponId() {
    return couponId;
  }

  public void setCouponId(int couponId) {
    this.couponId = couponId;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }
}
