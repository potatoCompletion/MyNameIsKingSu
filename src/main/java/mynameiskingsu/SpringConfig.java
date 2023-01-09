package mynameiskingsu;

import mynameiskingsu.common.JwtAuthenticationFilter;
import mynameiskingsu.common.JwtTokenProvider;
import mynameiskingsu.common.LoginFailureHandler;
import mynameiskingsu.common.LoginSuccessHandler;
import mynameiskingsu.repository.MemberRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    public SpringConfig(MemberRepository memberRepository,
                        AuthenticationManagerBuilder authenticationManagerBuilder,
                        JwtTokenProvider jwtTokenProvider,
                        JwtAuthenticationFilter jwtAuthenticationFilter,
                        LoginSuccessHandler loginSuccessHandler,
                        LoginFailureHandler loginFailureHandler) {
        this.memberRepository = memberRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailureHandler = loginFailureHandler;
    }

}
