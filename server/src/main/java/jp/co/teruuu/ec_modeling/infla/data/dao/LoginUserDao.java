package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.infla.security.LoginUser;

public interface LoginUserDao {

  LoginUser findByName(String name);;
}
