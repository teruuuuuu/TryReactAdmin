package jp.co.teruuu.ec_modeling.app.domain.order.model;

import java.io.Serializable;

public class OrderId implements Serializable {
  public static final long serialVersionUID = 12;
  int id = -1;
  public OrderId(){};
  public OrderId(int id){
    this.id = id;
  }

  public boolean isPersisted(){
    return this.id != -1;
  }

  public boolean equals(OrderId other){
    return this.id != -1 && this.id == other.id;
  }

  public int getId() {
    return id;
  }
}
