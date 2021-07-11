package jp.co.teruuu.ec_modeling.app.domain.order.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PaymentInfo implements Serializable {
  public static final long serialVersionUID = 18;

  PaymentId paymentId;
  PaymentType paymentType;
  int isPayed;
  int price;
  LocalDateTime dueDate;
  LocalDateTime paymentDate;
  PayDetail payDetail;

  public PaymentInfo(){this.paymentId = new PaymentId();};

  public PaymentInfo(OrderId orderId, PaymentType paymentType,
                     int price, LocalDateTime dueDate, LocalDateTime paymentDate) {
    this.paymentId = new PaymentId();
    this.paymentType = paymentType;
    this.price = price;
    this.dueDate = dueDate;
    this.paymentDate = paymentDate;
  }

  public boolean isPersisted(){
    return this.getPaymentId().isPersisted();
  }


  public PaymentId getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(PaymentId paymentId) {
    this.paymentId = paymentId;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public PayDetail getPayDetail() {
    return payDetail;
  }

  public void setPayDetail(PayDetail payDetail) {
    this.payDetail = payDetail;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDateTime getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDateTime paymentDate) {
    this.paymentDate = paymentDate;
  }

  public int getIsPayed() {
    return isPayed;
  }

  public void setIsPayed(int isPayed) {
    this.isPayed = isPayed;
  }
}
