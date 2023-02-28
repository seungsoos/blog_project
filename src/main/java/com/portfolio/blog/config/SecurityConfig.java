package com.portfolio.blog.config;


import com.portfolio.blog.constant.Role;
import com.portfolio.blog.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/assets/**", "/fonts/**").permitAll()
                .antMatchers("/", "/login/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/blog/**").permitAll()
                .antMatchers("/blog-information-api/**").permitAll()
                .anyRequest().authenticated()
        ;

        http.formLogin()
                .loginPage("/login/loginMain") //로그인페이지 주소
                .defaultSuccessUrl("/blog/blogMain/0") // 로그인 성공시 이동주소
                .usernameParameter("id") //유저네임 변수명
                .passwordParameter("password") // 패스워드 변수명
                .failureUrl("/login/loginMain/error") //로그인 실패시 이동주소
                .loginProcessingUrl("/login/loginMain")
                .permitAll()
        ;


        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login/loginMain")
        ;


        http.logout()
                .logoutUrl("/login/logout")
                .logoutSuccessUrl("/login/loginMain")
                .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation()
                .changeSessionId()
                .invalidSessionUrl("/login/loginMain/0")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login/loginMain"))
                ;

        /*http.headers()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());*/


        return  http.build();

    }

}