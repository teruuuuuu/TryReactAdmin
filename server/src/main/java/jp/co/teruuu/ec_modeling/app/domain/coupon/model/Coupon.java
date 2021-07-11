package jp.co.teruuu.ec_modeling.app.domain.coupon.model;

import java.io.Serializable;

public class Coupon implements Serializable{
  int couponId;
  String couponNumber;
  int discount;

  public Coupon(){};
  public Coupon(String couponNumber, int discount) {
    this.couponNumber = couponNumber;
    this.discount = discount;
  }

  public int getCouponId() {
    return couponId;
  }

  public void setCouponId(int couponId) {
    this.couponId = couponId;
  }

  public String getCouponNumber() {
    return couponNumber;
  }

  public void setCouponNumber(String couponNumber) {
    this.couponNumber = couponNumber;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }
}
