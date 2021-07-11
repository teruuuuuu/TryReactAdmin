package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.order.model.Order;

import java.util.HashMap;
import java.util.Map;

public interface OrderDao {
  int insert(Order order);
  default int update(Order order){
    Map<String, Object> map = new HashMap<String, Object>(){
      {put("orderId", order.getOrderId().getId());}
      {put("orderStatus", order.getOrderStatus().getCode());}
    };
    update(map);
    return 1;
  }
  Order findByUserIdStatus(int userId, int orderStatus);
  Order findByUserIdOrderId(int userId, int orderId);

  int update(Map<String, Object> param);
}
