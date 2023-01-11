package mynameiskingsu.controller;

import mynameiskingsu.common.JwtTokenProvider;
import mynameiskingsu.common.TokenInfo;
import mynameiskingsu.domain.Member;
import mynameiskingsu.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(MemberForm form) {
        Member member = Member.builder()
                        .userId(form.getUserId())
                        .userPassword(form.getUserPassword())
                        .build();

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/memberlist")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

    // 아이디 패스워드 권한인증 후 토큰 발급 메서드 (현재는 spring security loginForm 으로 대체)
//    @PostMapping("/login")
//    public String login(LoginForm form, Model model) {
//        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
//        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(form.getId(), form.getPassword());
//
//        // 2. 실제 검증(사용자 비밀번호 체크) 이 이루어지는 부분
//        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        // 3. 인증 정보를 기반으로 JWT 토큰 생성
//        var tokenInfo = jwtTokenProvider.generateToken(authentication);
//
//        model.addAttribute("token", tokenInfo);
//
//        return "success";
//    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
