package jp.co.teruuu.ec_modeling.app.domain.user.repository;

import jp.co.teruuu.ec_modeling.app.domain.user.UserEntity;
import jp.co.teruuu.ec_modeling.app.domain.user.model.User;
import jp.co.teruuu.ec_modeling.app.domain.user.model.UserId;
import jp.co.teruuu.ec_modeling.app.domain.user.model.UserInfo;
import jp.co.teruuu.ec_modeling.infla.data.dao.UserDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

  @Autowired
  UserDao userDao;

  @Autowired
  UserInfoDao userInfoDao;

  public Optional<User> findByName(String name) {
    User user = userDao.findByName(name);
    if(user == null){
      return Optional.empty();
    } else {
      return Optional.of(user);
    }
  }

  public Optional<User> findByNamePassword(String name, String password) {
    User user = userDao.findByNamePassword(name, password);
    if(user == null){
      return Optional.empty();
    } else {
      return Optional.of(user);
    }
  }

  public Optional<UserEntity> findById(int userId){
    User user = userDao.findById(userId);
    UserInfo userInfo = userInfoDao.findById(userId);
    if(user == null || userInfo == null){
      return Optional.empty();
    } else {
      user.setUserInfo(userInfo);
      return Optional.of(new UserEntity(user));
    }
  }

  public int save(UserEntity userEntity){
    if(!userEntity.isPersisted()){
      return insert(userEntity);
    } else {
      return 1;
    }
  }

  private int insert(UserEntity userEntity){
    userDao.insert(userEntity.user);
    final UserId userId = userEntity.user.getUserId();
    UserInfo userInfo = userEntity.user.getUserInfo();
    userInfoDao.insert(userId.getId(), userInfo.getAddress(), userInfo.getPostalCode());
    return 1;
  }
}
