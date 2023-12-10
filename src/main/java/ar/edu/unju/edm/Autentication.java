package ar.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class Autentication implements AuthenticationSuccessHandler{
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
    UserDetails details = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("Administrator")) {
                response.sendRedirect("/administration");
                return;
            } else if (authority.getAuthority().equals("Staff")) {
                response.sendRedirect("/staff");
                return;
            }
        }
        response.sendRedirect("/explorer");
  }
  
}
