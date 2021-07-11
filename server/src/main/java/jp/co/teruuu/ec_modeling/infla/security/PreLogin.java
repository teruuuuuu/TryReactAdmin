package jp.co.teruuu.ec_modeling.infla.security;

import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "preflight")
public class PreLogin {
  @GetMapping
  public String preLogin(HttpServletRequest request) {
    return "";
  }
}
