package mynameiskingsu;

import mynameiskingsu.common.JwtAuthenticationFilter;
import mynameiskingsu.common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import mynameiskingsu.common.LoginFailureHandler;
import mynameiskingsu.common.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()  // csrf 정책 미적용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용 비활성화 (토큰 사용하기 때문)
                .and()
                .authorizeRequests()
                    .antMatchers("/", "/members/new").permitAll()  // 권한없이 접근가능한 url
                    .anyRequest().authenticated()  // 모든 요청에 대해 인증된 사용자만 가능
                .and()
                .formLogin()
                    .loginPage("/")  // 로그인 페이지 url(GET, loginView 반환)
                    .successHandler(loginSuccessHandler)  // 로그인 성공 핸들러
                    .failureHandler(loginFailureHandler)  // 로그인 실패 핸들러
                    .usernameParameter("id")  // 넘겨주는 로그인 파라미터 (기본 username, 나의 경우 id로 세팅)
                    .passwordParameter("password")  // id와 같음
                    .loginProcessingUrl("/login")  // 로그인 프로세스 url (직접 구현 x, 내부 생성)
                    .permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // securityContext 이용하기 위함. 없을 시 토큰 발급 기록불가

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}