package shop.mtcoding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import shop.mtcoding.bank.domain.user.UserEnum;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.debug("디버그 : BCryptPasswordEncoder 빈 등록됨");
        return new BCryptPasswordEncoder();
    }

    // JWT 필터 등록이 필요정

    // JWT 서버 -> 세션 사용 안함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())) // iframe 허용안함
            .csrf(csrfConfigurer -> csrfConfigurer.disable()) // enable이면 포스트맨 작동안함, csrf: 권한을 도용당한 클라이언트가 가짜 요청을 서버에 전송
            .cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource()))
            .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // jSessionId를 서버쪽에서 관리안하겠다.
            .formLogin(formLoginConfigurer -> formLoginConfigurer.disable()) // react, 앱으로 요청할 예정
            .httpBasic(httpBasicConfigurer -> httpBasicConfigurer.disable()) // httpBasic은 브라우저가 팝업창을 이용해서 사용자 인증을 진행한다.
            .authorizeHttpRequests(requestMatcherRegistry ->
                    requestMatcherRegistry
                            .requestMatchers("/api/s/**").authenticated()
                            .requestMatchers("/api/admin/**").hasRole(UserEnum.ADMIN.getValue())
            );

        return http.build();
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE 등 javascript 요청 허용
        configuration.addAllowedOriginPattern("*"); // 모든 주소 허용
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 주소 요청에 위에 설정한 configuration을 넣어줌
        return source;
    }
}
