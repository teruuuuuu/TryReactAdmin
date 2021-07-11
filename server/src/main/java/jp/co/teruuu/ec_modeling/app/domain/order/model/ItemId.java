package jp.co.teruuu.ec_modeling.app.domain.order.model;

public class ItemId {
  public static final long serialVersionUID = 17;
  int id = -1;
  public ItemId(){};
  public ItemId(int id){
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
