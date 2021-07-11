package jp.co.teruuu.ec_modeling.init_data;

import jp.co.teruuu.ec_modeling.app.domain.coupon.model.Coupon;
import jp.co.teruuu.ec_modeling.app.domain.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponInitService {
  @Autowired
  CouponRepository couponRepository;

  public void insert(){
    datas().forEach(d -> {
      couponRepository.insert(d);
    });
  }

  private List<Coupon> datas(){
    List<Coupon> ret = new ArrayList<Coupon>();
    ret.add(new Coupon("abcdefghijklmn", 1000));
    ret.add(new Coupon("12345678901234", 1500));
    ret.add(new Coupon("1234567890abcd", 2000));
    return ret;
  }

}
