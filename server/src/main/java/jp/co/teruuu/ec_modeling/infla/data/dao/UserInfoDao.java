package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.user.model.UserInfo;

public interface UserInfoDao {
  UserInfo findById(int userId);
  int insert(int userId, String address, String postalCode);
}
