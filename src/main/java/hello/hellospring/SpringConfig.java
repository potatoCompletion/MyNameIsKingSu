package hello.hellospring;

import hello.hellospring.common.JwtTokenProvider;
import hello.hellospring.repository.MemberRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class SpringConfig {

    // EntityManager는 스프링에서 관리하는 객체로 생성자가 하나인 경우 의존성 주입 시 @Autowired를 생략해도 됩니다.
    // @PersistenceContext 기본적으로는 사용해야 하지만 생략하더라도 스프링 부트에서 DI 처리를 해 줍니다.

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    public SpringConfig(MemberRepository memberRepository,
                        AuthenticationManagerBuilder authenticationManagerBuilder,
                        JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService();
//    }

}
