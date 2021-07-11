package jp.co.teruuu.ec_modeling.app.domain.product.model;

import java.io.Serializable;

public class ProductId implements Serializable {
  public static final long serialVersionUID = 11;
  int id = -1;
  public ProductId(){};
  public ProductId(int id){
    this.id = id;
  }

  public boolean isPersisted(){
    return this.id != -1;
  }

  public boolean equals(ProductId other){
    return this.id != -1 && this.id == other.id;
  }

  public int getId() {
    return id;
  }
}
