package jp.co.teruuu.ec_modeling.infla.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 認証成功時のハンドラ
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    MappingJackson2HttpMessageConverter httpMessageConverter;

    public void onAuthenticationSuccess(HttpServletRequest request,   HttpServletResponse response, Authentication authentication) throws IOException  {
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("name", authentication.getName());
        httpMessageConverter.write(resMap, MediaType.APPLICATION_JSON, outputMessage);
        response.setStatus(HttpStatus.OK.value()); // 200 OK.
    }
}