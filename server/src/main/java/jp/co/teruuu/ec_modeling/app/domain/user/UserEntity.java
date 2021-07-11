package jp.co.teruuu.ec_modeling.app.domain.user;

import jp.co.teruuu.ec_modeling.app.domain.user.model.User;

public class UserEntity {
  public User user;

  public UserEntity(User user){
    this.user = user;
  }

  public boolean isPersisted(){
    return this.user.getUserId().isPersisted();
  }

  public boolean equals(UserEntity other){
    return this.user.getUserId().equals(other.user.getUserId());
  }
}
