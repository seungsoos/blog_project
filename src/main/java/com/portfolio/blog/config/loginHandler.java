package com.portfolio.blog.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class loginHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        log.info("-----------------------------------------");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(auth);
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info(SecurityContextHolder.getContext().getAuthentication());
        log.info("-----------------------------------------");

        response.sendRedirect("/blog/memberBlogList");
    }
}
