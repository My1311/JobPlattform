package de.hbrs.se2.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy;

    public CustomAuthenticationSuccessHandler() {
        redirectStrategy = new DefaultRedirectStrategy();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/";

//        for (GrantedAuthority authority : authorities) {
//            if (authority.getAuthority().equals("ROLE_ADMIN")) {
//                redirectUrl = "/admin/dashboard";
//                break;
//            } else if (authority.getAuthority().equals("ROLE_USER")) {
//                redirectUrl = "/user/home";
//                break;
//            }
//        }

        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }
}
