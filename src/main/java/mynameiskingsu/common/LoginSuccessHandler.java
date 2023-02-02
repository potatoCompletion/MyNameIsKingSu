package mynameiskingsu.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component(value = "authenticationSuccessHandler")
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final String AT_HEADER = "AT_HEADER";
    private final String RT_HEADER = "RT_HEADER";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    FilterChain chain, Authentication authentication) {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);  // 클라이언트가 원래 접근하려던 url 확인 용도

        var tokenInfo = jwtTokenProvider.generateToken(authentication);
        response.setHeader(AT_HEADER, tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken()); // Header에 accessToken 추가
        response.setHeader(RT_HEADER, tokenInfo.getGrantType() + " " + tokenInfo.getRefreshToken()); // Header에 refreshToken 추가

        PrintWriter writer = response.getWriter();
        writer.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(tokenInfo));

//        String url = "/members/success"; // set default url
//
//        if (savedRequest != null) {
//            url = savedRequest.getRedirectUrl();
//        }   // 클라이언트가 원래 접근하려던 url이 있을 경우
//
//        redirectStrategy.sendRedirect(request, response, url);
    }
}
