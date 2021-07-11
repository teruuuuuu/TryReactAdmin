package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;
import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductInfo;

import java.util.List;

public interface ProductInfoDao {
  ProductInfo findById(int productId);
  int insert(int productId, String description);
}
