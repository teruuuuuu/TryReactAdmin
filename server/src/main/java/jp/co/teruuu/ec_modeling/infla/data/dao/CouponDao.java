package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.coupon.model.Coupon;

public interface CouponDao {
  int insert(Coupon coupon);
  Coupon findByCouponNumber(String couponNumber);
}
