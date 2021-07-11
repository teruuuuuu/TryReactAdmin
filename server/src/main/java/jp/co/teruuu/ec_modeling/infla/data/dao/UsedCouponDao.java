package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.order.model.UsedCoupon;

import java.util.List;

public interface UsedCouponDao {
  public int insert(int orderId, int couponId, int discount);
  public int deleteByOrderId(int orderId);
  public List<UsedCoupon> findByOrderId(int orderId);
}
