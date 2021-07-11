package jp.co.teruuu.ec_modeling.app.app_service.repository;

import jp.co.teruuu.ec_modeling.controller.product.dto.ItemDetail;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import jp.co.teruuu.ec_modeling.infla.data.dao.ItemDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDetailRepository {
  @Autowired
  ItemDetailDao itemDetailDao;

  public List<ItemDetail> findByOrderId(OrderId orderId){
    return itemDetailDao.findByOrderId(orderId);
  }
}
