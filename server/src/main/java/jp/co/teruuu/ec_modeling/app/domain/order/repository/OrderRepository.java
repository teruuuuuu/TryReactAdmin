package jp.co.teruuu.ec_modeling.app.domain.order.repository;

import jp.co.teruuu.ec_modeling.app.domain.order.OrderEntity;
import jp.co.teruuu.ec_modeling.app.domain.order.model.Item;
import jp.co.teruuu.ec_modeling.app.domain.order.model.Order;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentInfo;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderStatus;
import jp.co.teruuu.ec_modeling.app.domain.order.model.UsedCoupon;
import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductId;
import jp.co.teruuu.ec_modeling.app.domain.user.model.UserId;
import jp.co.teruuu.ec_modeling.infla.data.dao.ItemDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.OrderDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.PaymentInfoDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.ProductDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.UsedCouponDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
  @Autowired
  OrderDao orderDao;

  @Autowired
  ItemDao itemDao;

  @Autowired
  ProductDao productDao;

  @Autowired
  PaymentInfoDao paymentInfoDao;

  @Autowired
  UsedCouponDao usedCouponDao;

  public OrderEntity getCurt(UserId userId){
    Order order = orderDao.findByUserIdStatus(userId.getId(), OrderStatus.Shopping.getCode());
    if(order == null){
      return new OrderEntity(userId.getId());
    } else {
     return makeOrderEntity(order);
    }
  }

  public Optional<OrderEntity> getByUserIdOrderId(int userId, int orderId) {
    Order order = orderDao.findByUserIdOrderId(userId, orderId);
    if(order == null){
      return Optional.empty();
    } else {
     return Optional.of(makeOrderEntity(order));
    }
  }

  public int save(OrderEntity orderEntity){
    OrderId orderId = syncOrder(orderEntity);
    syncItems(orderId, orderEntity.getItems());
    syncPaymentInfo(orderId, orderEntity.getPaymentInfo());
    syncCoupon(orderId, orderEntity.getUsedCoupons());
    return 1;
  }

  public int updateItem(OrderEntity orderEntity, ProductId productId) {
    Optional<Item> item = orderEntity.getItems().stream().filter(i-> i.getProductId().equals(productId)).findAny();
    if(item.isPresent()){
      OrderId orderId = syncOrder(orderEntity);
      syncItem(orderId, item.get());
      return 1;
    } else {
      return 0;
    }
  }

  private OrderEntity makeOrderEntity(Order order){
    OrderId orderId = order.getOrderId();
    List<Item> items = itemDao.findByOrderId(orderId);
    items.forEach(i-> {
      i.setCurrentPrice(productDao.findById(i.getProductId().getId()).getPrice());
    });
    PaymentInfo paymentInfo = paymentInfoDao.findByOrderId(orderId.getId());
    List<UsedCoupon> usedCoupons = usedCouponDao.findByOrderId(orderId.getId());
    return new OrderEntity(order, items, Optional.ofNullable(paymentInfo), usedCoupons);
  }

  private OrderId syncOrder(OrderEntity orderEntity){
    if(!orderEntity.isPersisted()){
      orderDao.insert(orderEntity.getOrder());
    } else {
      orderDao.update(orderEntity.getOrder());
    }
    return orderEntity.getOrder().getOrderId();
  }

  private void syncItems(OrderId orderId, List<Item> items) {
    items.forEach(i->{
      syncItem(orderId, i);
    });
  }

  private void syncItem(OrderId orderId, Item item) {
    if(item.getItemId().isPersisted()){
      if(item.getNumber() > 0){
        itemDao.update(orderId, item);
      } else {
        itemDao.deleteByOrderIdProductId(orderId, item.getProductId());
      }
    } else {
      if(item.getNumber() > 0){
        itemDao.insert(orderId, item);
      }
    }
  }

  private void syncPaymentInfo(OrderId orderId, Optional<PaymentInfo> paymentInfoOp){
    if(!paymentInfoOp.isPresent()){
      return;
    }
    PaymentInfo paymentInfo = paymentInfoOp.get();
    if(paymentInfo.isPersisted()){
      paymentInfoDao.update(orderId, paymentInfo);
    } else {
      paymentInfoDao.insert(orderId, paymentInfo);
    }
  }

  private void syncCoupon(OrderId orderId, List<UsedCoupon> usedCoupons){
    usedCouponDao.deleteByOrderId(orderId.getId());
    usedCoupons.forEach(uc->{
      usedCouponDao.insert(orderId.getId(), uc.getCouponId(), uc.getDiscount());
    });
  }
}
