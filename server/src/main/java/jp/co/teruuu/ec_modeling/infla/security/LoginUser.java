package jp.co.teruuu.ec_modeling.infla.security;

import jp.co.teruuu.ec_modeling.app.domain.user.model.UserId;

import java.io.Serializable;

public class LoginUser implements Serializable{
  private UserId userId;
  private String name;
  private String password;

  public UserId getUserId() {
    return userId;
  }

  public void setUserId(UserId userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
