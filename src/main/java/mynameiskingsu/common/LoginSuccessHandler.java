package mynameiskingsu.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    FilterChain chain, Authentication authentication) {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var tokenInfo = jwtTokenProvider.generateToken(authentication);
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String url = "/members/success"; // set default url

        response.setHeader("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken());
        PrintWriter writer = response.getWriter();
        writer.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(tokenInfo));  // json pretty response body에 입력

//        if (savedRequest != null) {
//            url = savedRequest.getRedirectUrl();
//        }
//
//
//
//        redirectStrategy.sendRedirect(request, response, url);
    }
}
