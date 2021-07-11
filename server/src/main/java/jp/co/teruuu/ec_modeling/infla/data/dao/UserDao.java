package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.user.model.User;

public interface UserDao {
  User findById(int userId);
  User findByName(String name);
  User findByNamePassword(String name, String password);

  int insert(User user);
}
