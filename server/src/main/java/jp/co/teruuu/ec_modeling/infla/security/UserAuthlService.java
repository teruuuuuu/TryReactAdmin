package jp.co.teruuu.ec_modeling.infla.security;

import jp.co.teruuu.ec_modeling.app.domain.user.model.User;
import jp.co.teruuu.ec_modeling.app.domain.user.repository.UserRepository;
import jp.co.teruuu.ec_modeling.infla.data.dao.LoginUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuthlService implements UserDetailsService {
    @Autowired
    LoginUserDao loginUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = loginUserDao.findByName(username);
        return new AuthUser(Optional.ofNullable(user));
    }


}
