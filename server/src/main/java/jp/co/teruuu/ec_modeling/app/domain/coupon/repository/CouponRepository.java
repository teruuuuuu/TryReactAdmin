package jp.co.teruuu.ec_modeling.app.domain.coupon.repository;

import jp.co.teruuu.ec_modeling.app.domain.coupon.model.Coupon;
import jp.co.teruuu.ec_modeling.infla.data.dao.CouponDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CouponRepository {
  @Autowired
  CouponDao couponDao;

  public int insert(Coupon coupon) {
    Coupon kizon = couponDao.findByCouponNumber(coupon.getCouponNumber());
    if(kizon != null){
      // 既に登録済み
      return -1;
    }
    couponDao.insert(coupon);
    return 1;
  }

  public Optional<Coupon> findByCouponNumber(String couponNumber) {
    return Optional.ofNullable(couponDao.findByCouponNumber(couponNumber));
  }
}
