package jp.co.teruuu.ec_modeling.app.domain.product.model;

public class Product {
  public static final long serialVersionUID = 3;

  ProductId productId;
  String name;
  int price;

  ProductInfo productInfo;

  public Product(){}; // Mybatisで使う
  public Product(String name, int price) {
    this.productId = new ProductId();
    this.name = name;
    this.price = price;
  }
  public Product(ProductId productId, String name, int price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }

  public ProductId getProductId() {
    return productId;
  }

  public void setProductId(ProductId productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public ProductInfo getProductInfo() {
    return productInfo;
  }

  public void setProductInfo(ProductInfo productInfo) {
    this.productInfo = productInfo;
  }

  public boolean isPersisted(){
    return this.productId.isPersisted();
  }
}
