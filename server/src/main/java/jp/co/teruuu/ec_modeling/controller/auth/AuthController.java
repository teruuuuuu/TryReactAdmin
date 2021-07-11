package jp.co.teruuu.ec_modeling.controller.auth;

import jp.co.teruuu.ec_modeling.infla.security.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class AuthController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<String> index(@AuthenticationPrincipal AuthUser user) {
        return Arrays.asList("hello");
//        return user;
    }

//    @RequestMapping(value = "auth/user", method = RequestMethod.GET)
//    public AuthUser auth(@AuthenticationPrincipal AuthUser user) {
//        return user;
//    }
}
