package jp.co.teruuu.ec_modeling.infla.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassEncoder {
  private static PasswordEncoder encoder = new BCryptPasswordEncoder();

  public static PasswordEncoder encoder(){
    return encoder;
  }
}
