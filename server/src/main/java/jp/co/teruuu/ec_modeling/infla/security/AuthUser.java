package jp.co.teruuu.ec_modeling.infla.security;

import jp.co.teruuu.ec_modeling.infla.encoder.PassEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;
import java.util.Optional;

public class AuthUser extends org.springframework.security.core.userdetails.User {

  // Userエンティティ
  private LoginUser user;

  public LoginUser getUser() {
    return user;
  }

  public AuthUser(Optional<LoginUser> user) {
    super(name(user), password(user), role(user));
    this.user = user.get();
  }

  private static final List<GrantedAuthority> NO_ROLES = AuthorityUtils.createAuthorityList("NO_ROLE");
  private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
  private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

  private static String name(Optional<LoginUser> user){
    if(user.isPresent()){
      return user.get().getName();
    } else {
      return "";
    }
  }

  private static String password(Optional<LoginUser> user){
    if(user.isPresent()){
      return user.get().getPassword();
    } else {
      return "";
    }
  }

  private static List<GrantedAuthority> role(Optional<LoginUser> user) {
    if(user.isPresent()){
      return USER_ROLES;
    } else {
      return NO_ROLES;
    }
  }
}
