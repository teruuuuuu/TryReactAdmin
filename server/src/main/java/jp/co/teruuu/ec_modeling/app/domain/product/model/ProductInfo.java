package jp.co.teruuu.ec_modeling.app.domain.product.model;

public class ProductInfo {
  public static final long serialVersionUID = 4;

  String description;

  public ProductInfo(){};
  public ProductInfo(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
}
