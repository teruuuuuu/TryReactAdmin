package jp.co.teruuu.ec_modeling.app.app_service;

import jp.co.teruuu.ec_modeling.controller.product.dto.ItemDetail;
import jp.co.teruuu.ec_modeling.app.app_service.repository.ItemDetailRepository;
import jp.co.teruuu.ec_modeling.app.domain.order.OrderEntity;
import jp.co.teruuu.ec_modeling.app.domain.order.exception.OrderException;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentType;
import jp.co.teruuu.ec_modeling.app.domain.order.repository.OrderRepository;
import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;
import jp.co.teruuu.ec_modeling.app.domain.product.repository.ProductRepository;
import jp.co.teruuu.ec_modeling.infla.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ItemDetailRepository itemDetailRepository;

  public boolean updateItem(LoginUser user, int productId, int number){
    return updateItemFunc(user, productId, number);
  }

  public boolean confirm(LoginUser user, PaymentType paymentType){
    return confirmFunc(user, paymentType);
  }

  public List<ItemDetail> curtInfo(LoginUser user){
    return curtInfoFunc(user);
  }

  @Transactional(readOnly = false)
  private boolean updateItemFunc(LoginUser user, int productId, int number){
    OrderEntity orderEntity = orderRepository.getCurt(user.getUserId());
    Optional<Product> product = productRepository.findByProductId(productId);

    if(product.isPresent()){
      try{
        orderEntity.updateItem(product.get().getProductId(), product.get().getPrice(), number);
        orderRepository.updateItem(orderEntity, product.get().getProductId());
        return true;
      } catch(OrderException e) {
        return false;
      }
    } else {
      return false;
    }
  }

  @Transactional(readOnly = false)
  private boolean confirmFunc(LoginUser user, PaymentType paymentType){
    OrderEntity orderEntity = orderRepository.getCurt(user.getUserId());
    try {
      orderEntity.confirm(paymentType);
      orderRepository.save(orderEntity);
    } catch(OrderException e){
      return false;
    }
    return true;
  }

  @Transactional(readOnly = true)
  private List<ItemDetail> curtInfoFunc(LoginUser user){
    OrderEntity orderEntity = orderRepository.getCurt(user.getUserId());
    return itemDetailRepository.findByOrderId(orderEntity.getOrder().getOrderId());
  }
}
