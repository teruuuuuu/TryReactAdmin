package jp.co.teruuu.ec_modeling.app.domain.order.model;

public class PaymentId {
  public static final long serialVersionUID = 16;
  int id = -1;
  public PaymentId(){};
  public PaymentId(int id){
    this.id = id;
  }

  public boolean isPersisted(){
    return this.id != -1;
  }

  public boolean equals(PaymentId other){
    return this.id != -1 && this.id == other.id;
  }

  public int getId() {
    return id;
  }
}
