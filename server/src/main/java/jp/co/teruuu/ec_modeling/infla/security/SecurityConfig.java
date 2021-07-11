package jp.co.teruuu.ec_modeling.infla.security;

import jp.co.teruuu.ec_modeling.infla.encoder.PassEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        web.ignoring().antMatchers(
                "/h2-console/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                // AUTHORIZE
                .mvcMatchers("/product/search")
                .permitAll()

                // USER
                .mvcMatchers("/", "/auth/**", "/default", "/order/**")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()

                // EXCEPTION
                .exceptionHandling()
                .authenticationEntryPoint(new AuthEntoryPoint())
//                .accessDeniedHandler(accessDeniedHandler())
                .and()

                // LOGIN
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .usernameParameter("name")
                .passwordParameter("password")
//                .successForwardUrl("/auth")
//                .failureUrl("/fail")
//                .successHandler(authSuccessHandler)
//                .failureHandler(new AuthFailHandler())
                .and()

                // CSRF
                .csrf()
                .disable()
                // CORS
                .cors()
                .configurationSource(corsConfigurationSource())
        ;
    }

    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        CustomLoginFilter filter = new CustomLoginFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        return filter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                UserAuthlService userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        auth.eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

//    @Bean
//    public SimpleUrlAuthenticationSuccessHandler handler() {
//        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
//        handler.setDefaultTargetUrl("/auth");
//        return handler;
//    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        // Access-Control-Allow-Methods
        configuration.setAllowedMethods(Arrays.asList("*"));
        // Access-Control-Allow-Headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Access-Control-Allow-Credentials
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PassEncoder.encoder();
    }

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
////        MyResult result = new MyResult("認証成功"); // JSONにするオブジェクト
//        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
//        httpMessageConverter.write(authentication, MediaType.APPLICATION_JSON, outputMessage); // Responseに書き込む
//        response.setStatus(HttpStatus.OK.value()); // 200 OK.
//    }

//    @Bean
//    SimpleUrlAuthenticationSuccessHandler successHandler() {
//        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//        successHandler.setRedirectStrategy(new RedirectStrategy() {
//            @Override
//            public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
//                response.sendRedirect("/auth");
//            }
//        });
//        return successHandler;
//    }


//    AuthenticationEntryPoint authenticationEntryPoint() {
//        return new AuthEntoryPoint();
//    }
//
//    AccessDeniedHandler accessDeniedHandler() {
//        return new UnauthorizedHandler();
//    }
//
//    AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new AuthSuccessHandler();
//    }
//
//    AuthenticationFailureHandler authenticationFailureHandler() {
//        return new AuthFailHandler();
//    }
//
//    LogoutSuccessHandler logoutSuccessHandler() {
//        return new HttpStatusReturningLogoutSuccessHandler();
//    }
}
