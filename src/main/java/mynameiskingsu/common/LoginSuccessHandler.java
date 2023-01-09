package mynameiskingsu.common;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component(value = "authenticationSuccessHandler")
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    FilterChain chain, Authentication authentication) {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var tokenInfo = jwtTokenProvider.generateToken(authentication);
//        request.getSession().setAttribute("token", tokenInfo.getAccessToken());

        response.setHeader("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken());
    }
}
