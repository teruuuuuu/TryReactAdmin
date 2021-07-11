package jp.co.teruuu.ec_modeling.app.domain.product.repository;

import jp.co.teruuu.ec_modeling.app.domain.product.ProductEntity;
import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductId;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductInfo;
import jp.co.teruuu.ec_modeling.infla.data.dao.ProductDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.ProductInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
  @Autowired
  ProductDao productDao;

  @Autowired
  ProductInfoDao productInfoDao;

  public Optional<Product> findByProductId(int productId) {
    Product product = productDao.findById(productId);
    if(product == null){
      return Optional.empty();
    } else {
      product.setProductInfo(productInfoDao.findById(productId));
      return Optional.of(product);
    }
  }

  public List<Product> searchProduct(String name, int limit, int offset){
    return productDao.search("%" + name + "%", limit, offset);
  }

  public int save(ProductEntity productEntity){
    if(!productEntity.product.isPersisted()){
      return insert(productEntity);
    } else {
      return 1;
    }
  }

  private int insert(ProductEntity productEntity){
    productDao.insert(productEntity.product);
    final ProductId productId = productEntity.product.getProductId();
    ProductInfo productInfo = productEntity.product.getProductInfo();
    productInfoDao.insert(productId.getId(), productInfo.getDescription());
    return 1;
  }
}
