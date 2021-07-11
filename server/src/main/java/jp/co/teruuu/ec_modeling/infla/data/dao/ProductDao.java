package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;

import java.util.List;

public interface ProductDao {
  Product findById(int productId);
  List<Product> search(String name, int limit, int offset);
  int insert(Product product);
}
