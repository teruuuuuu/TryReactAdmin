package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.controller.product.dto.ItemDetail;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemDetailDao {
  List<ItemDetail> findByOrderId(Map<String, Object> param);
  default List<ItemDetail> findByOrderId(OrderId orderId) {
    return findByOrderId(new HashMap<String, Object>(){
      {put("orderId", orderId.getId());}
    });
  }
}
