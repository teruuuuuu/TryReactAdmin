package jp.co.teruuu.ec_modeling.init_data;

import jp.co.teruuu.ec_modeling.app.domain.product.ProductEntity;
import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductInfo;
import jp.co.teruuu.ec_modeling.app.domain.product.repository.ProductRepository;
import jp.co.teruuu.ec_modeling.app.domain.user.model.User;
import jp.co.teruuu.ec_modeling.app.domain.user.model.UserInfo;
import jp.co.teruuu.ec_modeling.app.domain.user.repository.UserRepository;
import jp.co.teruuu.ec_modeling.infla.encoder.PassEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInitService {
  @Autowired
  ProductRepository productRepository;

  public void insert(){
    for(ProductEntity p: productDatas()){
      productRepository.save(p);

    }
  }

  List<ProductEntity> productDatas(){
    List<ProductEntity> datas = new ArrayList<ProductEntity>();
    datas.add(makeProduct("product1", 100, "product1"));
    datas.add(makeProduct("product2", 200, "product2"));
    datas.add(makeProduct("product3", 300, "product3"));
    datas.add(makeProduct("product4", 400, "product4"));
    datas.add(makeProduct("product5", 500, "product5"));
    datas.add(makeProduct("product6", 600, "product6"));
    datas.add(makeProduct("product7", 700, "product7"));
    datas.add(makeProduct("product8", 800, "product8"));
    datas.add(makeProduct("product9", 900, "product9"));
    datas.add(makeProduct("product10", 1000, "product10"));
    return datas;
  }

  ProductEntity makeProduct(String name, int price, String description){
    Product p = new Product( name, price);
    p.setProductInfo(new ProductInfo(description));
    return new ProductEntity(p);
  }
}