package jp.co.teruuu.ec_modeling.app.domain.user.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
  public static final long serialVersionUID = 2;

  private String address;
  private String postalCode;

  public UserInfo(){};
  public UserInfo(String address, String postalCode) {
    this.address = address;
    this.postalCode = postalCode;
  }
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
