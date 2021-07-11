package jp.co.teruuu.ec_modeling.init_data;

import jp.co.teruuu.ec_modeling.app.domain.coupon.model.Coupon;
import jp.co.teruuu.ec_modeling.app.domain.coupon.repository.CouponRepository;
import jp.co.teruuu.ec_modeling.app.domain.order.OrderEntity;
import jp.co.teruuu.ec_modeling.app.domain.order.exception.ItemUpdateException;
import jp.co.teruuu.ec_modeling.app.domain.order.exception.OrderException;
import jp.co.teruuu.ec_modeling.app.domain.order.model.Item;
import jp.co.teruuu.ec_modeling.app.domain.order.model.Order;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentType;
import jp.co.teruuu.ec_modeling.app.domain.order.model.UsedCoupon;
import jp.co.teruuu.ec_modeling.app.domain.order.repository.OrderRepository;
import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;
import jp.co.teruuu.ec_modeling.app.domain.product.repository.ProductRepository;
import jp.co.teruuu.ec_modeling.app.domain.user.model.User;
import jp.co.teruuu.ec_modeling.app.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInitService {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CouponRepository couponRepository;

  User user1;
  User user2;
  User user3;

  public void insert(){
    try{
      datas().forEach(d -> {
        orderRepository.save(d);
      });
    } catch (OrderException e){
      e.printStackTrace();
    }

  }

  private List<OrderEntity> datas() throws OrderException{
    user1 = userRepository.findByName("user1").get();
    user2 = userRepository.findByName("user2").get();
    user3 = userRepository.findByName("user3").get();
    List<Product> products = productRepository.searchProduct("", 100, 0);
    Product product1 = products.get(0);
    Product product2 = products.get(1);
    Product product3 = products.get(2);
    Product product4 = products.get(3);
    Product product5 = products.get(4);
    Product product6 = products.get(5);
    Product product7 = products.get(6);

    Item item1 = new Item(product1.getProductId(), product1.getPrice(), product1.getPrice(), 1, LocalDateTime.now());
    Item item2 = new Item(product2.getProductId(), product2.getPrice(), product2.getPrice(), 2, LocalDateTime.now());
    Item item3 = new Item(product3.getProductId(), product3.getPrice(), product3.getPrice(), 3, LocalDateTime.now());
    Item item4 = new Item(product4.getProductId(), product4.getPrice(), product4.getPrice(), 4, LocalDateTime.now());
    Item item5 = new Item(product5.getProductId(), product5.getPrice(), product5.getPrice(), 5, LocalDateTime.now());
    Item item6 = new Item(product6.getProductId(), product6.getPrice(), product6.getPrice(), 6, LocalDateTime.now());
    Item item7 = new Item(product7.getProductId(), product7.getPrice(), product7.getPrice(), 7, LocalDateTime.now());

    Coupon coupon1 = couponRepository.findByCouponNumber("abcdefghijklmn").get();
    Coupon coupon2 = couponRepository.findByCouponNumber("12345678901234").get();

    List<OrderEntity> ret = new ArrayList<OrderEntity>();
    OrderEntity order1 = makeOrderEntity(user1);
    order1
            .updateItem(product1.getProductId(), product1.getPrice(), 1)
            .updateItem(product2.getProductId(), product2.getPrice(), 2)
            .updateItem(product3.getProductId(), product3.getPrice(), 3)
            .addCoupon(new UsedCoupon(coupon1.getCouponId(), coupon1.getDiscount()))
            .confirm(PaymentType.Credit)
            .payComp();
    ret.add(order1);

    OrderEntity order2 = makeOrderEntity(user2);
    order2
            .updateItem(product4.getProductId(), product4.getPrice(), 4)
            .updateItem(product5.getProductId(), product5.getPrice(), 5)
            .updateItem(product6.getProductId(), product6.getPrice(), 6)
            .updateItem(product7.getProductId(), product7.getPrice(), 7)
            .addCoupon(new UsedCoupon(coupon2.getCouponId(), coupon2.getDiscount()))
            .confirm(PaymentType.Bank);
    order2.addCoupon(new UsedCoupon(coupon2.getCouponId(), coupon2.getDiscount()));
    order2.confirm(PaymentType.Bank);
    ret.add(order2);

    OrderEntity order3 = makeOrderEntity(user3);
    order3.updateItem(product7.getProductId(), product7.getPrice(), 7);
    ret.add(order3);
    return ret;
  }

  private OrderEntity makeOrderEntity(User user){
    OrderEntity orderEntity = orderRepository.getCurt(user.getUserId());
    return orderEntity;
  }
}
