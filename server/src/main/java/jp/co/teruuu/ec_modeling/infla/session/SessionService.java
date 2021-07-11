package jp.co.teruuu.ec_modeling.infla.session;

import jp.co.teruuu.ec_modeling.app.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

enum Key {
  User("user"),
  ;

  private final String key;
  private Key(final String key) { this.key = key; }
  public String toString() { return this.key; }
}

@Service
public class SessionService {

  protected enum SKey {
    user
  };

  @Autowired
  HttpSession httpSession;

  public Optional<User> getUser(){
    return Optional.ofNullable((User)httpSession.getAttribute(Key.User.toString()));
  }

  public void setUser(User user){
    httpSession.setAttribute(Key.User.toString(), user);
  }
}
