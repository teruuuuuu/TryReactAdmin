package jp.co.teruuu.ec_modeling.app.domain.user.model;

import java.io.Serializable;

public class User implements Serializable {
  public static final long serialVersionUID = 1;

  private UserId userId;
  private String name;
  private String password;
  private UserInfo userInfo;

  public User(){};
  public User(String name, String password) {
    this.userId = new UserId();
    this.name = name;
    this.password = password;
  }
  public User(UserId userId, String name, String password) {
    this.userId = userId;
    this.name = name;
    this.password = password;
  }

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

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }
}
