package jp.co.teruuu.ec_modeling.init_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class InitMain implements ApplicationListener<ContextRefreshedEvent> {
  @Autowired
  UserInitService userInitService;

  @Autowired
  ProductInitService productInitService;

  @Autowired
  CouponInitService couponInitService;

  @Autowired
  OrderInitService orderInitService;

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    System.out.println("start");
    userInitService.insert();
    productInitService.insert();
    couponInitService.insert();
    orderInitService.insert();
  }
}