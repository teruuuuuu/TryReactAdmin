package jp.co.teruuu.ec_modeling.app.domain.order;

import jp.co.teruuu.ec_modeling.app.domain.order.exception.ItemUpdateException;
import jp.co.teruuu.ec_modeling.app.domain.order.exception.OrderConfirmException;
import jp.co.teruuu.ec_modeling.app.domain.order.exception.OrderException;
import jp.co.teruuu.ec_modeling.app.domain.order.model.Item;
import jp.co.teruuu.ec_modeling.app.domain.order.model.Order;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentInfo;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderStatus;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentType;
import jp.co.teruuu.ec_modeling.app.domain.order.model.UsedCoupon;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderEntity {
  Order order;
  List<Item> items;
  Optional<PaymentInfo> paymentInfo;
  List<UsedCoupon> usedCoupons;

  // カート新規作成時
  public OrderEntity(int userId){
    this.order = new Order(userId, OrderStatus.Shopping);
    this.items = new ArrayList<Item>();
    this.paymentInfo = Optional.empty();
    this.usedCoupons = new ArrayList<UsedCoupon>();
  }
  // 永続化後
  public OrderEntity(Order order, List<Item> items, Optional<PaymentInfo> paymentInfo, List<UsedCoupon> usedCoupons) {
    this.order = order;
    this.items = items;
    this.paymentInfo = paymentInfo;
    this.usedCoupons = usedCoupons;
  }

  public boolean isPersisted(){
    return this.order.isPersisted();
  }

  public OrderEntity updateItem(ProductId productId, int price, int number) throws OrderException{
    if(!order.canItemUpdate()){
      throw new ItemUpdateException("cant item update");
    }
    Optional<Item> kizon = this.items.stream()
            .filter(i->i.getProductId().equals(productId)).findAny();
    if(kizon.isPresent()){
      Item item = kizon.get();
      item.setPrice(price);
      item.setCurrentPrice(price);
      item.setNumber(number);
      item.setUpdateDate(LocalDateTime.now());
    } else {
      this.items.add(new Item(productId, price, price, number, LocalDateTime.now()));
    }
    return this;
  }
  public OrderEntity addCoupon(UsedCoupon usedCoupon) {
    this.usedCoupons.add(usedCoupon);
    return this;
  }

  public OrderEntity confirm(PaymentType paymentType) throws OrderException {
    if(this.items.size() == 0) {
      throw new OrderConfirmException("order confirm error");
    }
    this.order.setOrderStatus(OrderStatus.Confirm);
    this.paymentInfo = Optional.of(new PaymentInfo(this.order.getOrderId(), paymentType,
            price(), LocalDateTime.now().plusDays(7), null));
    return this;
  }

  public OrderEntity payComp(){
    this.paymentInfo.get().setIsPayed(1);
    this.paymentInfo.get().setPaymentDate(LocalDateTime.now());
    return this;
  }


  private int price(){
    int priceSum = items.stream().reduce(
            0,
            (sum, elem) -> sum + elem.sum(),
            (sum1, sum2) -> sum1 + sum2);
    int discountSum = usedCoupons.stream().reduce(
            0,
            (sum, elem) -> sum + elem.getDiscount(),
            (sum1, sum2) -> sum1 + sum2);
    int result = priceSum - discountSum;
    if(result < 0){
      result = 0;
    }
    return result;
  }

  public Order getOrder() {
    return order;
  }

  public List<Item> getItems() {
    return items;
  }

  public Optional<PaymentInfo> getPaymentInfo() {
    return paymentInfo;
  }

  public List<UsedCoupon> getUsedCoupons() {
    return usedCoupons;
  }
}
