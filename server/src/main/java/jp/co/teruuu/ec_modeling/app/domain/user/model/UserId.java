package jp.co.teruuu.ec_modeling.app.domain.user.model;

import java.io.Serializable;

public class UserId implements Serializable{
  public static final long serialVersionUID = 10;
  int id = -1;

  public UserId(){};
  public UserId(int id){
    this.id = id;
  }

  public boolean isPersisted(){
    return this.id != -1;
  }

  public boolean equals(UserId other){
    return this.id != -1 && this.id == other.id;
  }

  public int getId() {
    return id;
  }
}
