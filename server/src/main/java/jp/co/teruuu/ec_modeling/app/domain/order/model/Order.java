package jp.co.teruuu.ec_modeling.app.domain.order.model;

import java.io.Serializable;

public class Order implements Serializable {
  public static final long serialVersionUID = 5;
  OrderId orderId;
  int userId;
  OrderStatus orderStatus;

  public Order(){this.orderId = new OrderId();};
  public Order(int userId, OrderStatus orderStatus) {
    this.orderId = new OrderId();
    this.userId = userId;
    this.orderStatus = orderStatus;
  }

  public Order(OrderId orderId, int userId, OrderStatus orderStatus) {
    this.orderId = orderId;
    this.userId = userId;
    this.orderStatus = orderStatus;
  }

  public boolean isPersisted() {
    return this.orderId.isPersisted();
  }

  public boolean canItemUpdate(){
    return orderStatus.equals(OrderStatus.Shopping);
  }

  public OrderId getOrderId() {
    return orderId;
  }

  public void setOrderId(OrderId orderId) {
    this.orderId = orderId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }
}
