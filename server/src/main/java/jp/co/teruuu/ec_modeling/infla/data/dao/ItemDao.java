package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.order.model.Item;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductId;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemDao {
  List<Item> findByOrderId(Map<String, Object> param);
  int insert(Map<String, Object> param);
  int update(Map<String, Object> param);
  int deleteByOrderId(Map<String, Object> param);
//  int deleteByOrderIdProductId(Map<String, Object> param);

  default List<Item> findByOrderId(OrderId orderId) {
    return findByOrderId(new HashMap<String, Object>(){
      {put("orderId", orderId.getId());}
    });
  }

  default int deleteByOrderId(OrderId orderId) {
    return deleteByOrderId(new HashMap<String, Object>(){
      {put("orderId", orderId.getId());}
    });
  }

  default int deleteByOrderIdProductId(OrderId orderId, ProductId productId) {
    return deleteByOrderId(new HashMap<String, Object>(){
      {put("orderId", orderId.getId());}
      {put("productId", productId.getId());}
    });
  }

  default Integer insert(OrderId orderId, Item item) {
    Map<String, Object> map = new HashMap<String, Object>(){
      {put("orderId", orderId.getId());}
      {put("productId", item.getProductId().getId());}
      {put("price", item.getPrice());}
      {put("number", item.getNumber());}
      {put("updateDate", item.getUpdateDate());}
    };
    insert(map);
    return (Integer)map.get("itemId");
  }

  default int update(OrderId orderId, Item item) {
    Map<String, Object> map = new HashMap<String, Object>(){
      {put("itemId", item.getItemId().getId());}
      {put("orderId", orderId.getId());}
      {put("productId", item.getProductId().getId());}
      {put("price", item.getPrice());}
      {put("number", item.getNumber());}
      {put("updateDate", item.getUpdateDate());}
    };
    update(map);
    return 1;
  }
}
