package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentInfo;

import java.util.HashMap;
import java.util.Map;

public interface PaymentInfoDao {
  PaymentInfo findByOrderId(int orderId);

  default int update(OrderId orderId, PaymentInfo paymentInfo){
    Map<String, Object> map = new HashMap<String, Object>(){
      {put("paymentId", paymentInfo.getPaymentId().getId());}
      {put("orderId", orderId.getId());}
      {put("paymentType", paymentInfo.getPaymentType().getCode());}
      {put("isPayed", paymentInfo.getIsPayed());}
      {put("price", paymentInfo.getPrice());}
      {put("dueDate", paymentInfo.getDueDate());}
      {put("paymentDate", paymentInfo.getPaymentDate());}
    };
    insert(map);
    return (Integer)map.get("paymentId");
  }
  default int insert(OrderId orderId, PaymentInfo paymentInfo){
    Map<String, Object> map = new HashMap<String, Object>(){
      {put("orderId", orderId.getId());}
      {put("paymentType", paymentInfo.getPaymentType().getCode());}
      {put("isPayed", paymentInfo.getIsPayed());}
      {put("price", paymentInfo.getPrice());}
      {put("dueDate", paymentInfo.getDueDate());}
      {put("paymentDate", paymentInfo.getPaymentDate());}
    };
    insert(map);
    return (Integer)map.get("paymentId");
  }

  int update(Map<String, Object> param);
  int insert(Map<String, Object> param);
}
