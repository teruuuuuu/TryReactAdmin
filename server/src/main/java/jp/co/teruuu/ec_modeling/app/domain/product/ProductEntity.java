package jp.co.teruuu.ec_modeling.app.domain.product;

import jp.co.teruuu.ec_modeling.app.domain.product.model.Product;

public class ProductEntity {
  public Product product;
  public ProductEntity(Product product){
    this.product = product;
  }

  public boolean equals(ProductEntity other){
    return this.product.getProductId().equals(other.product.getProductId());
  }

  public boolean isPersisted(Product product){
    return this.product.getProductId().isPersisted();
  }
}
