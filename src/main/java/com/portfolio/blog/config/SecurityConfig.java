package com.portfolio.blog.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/assets/**").permitAll()
                .antMatchers("/", "/blog/**").permitAll()
                .antMatchers("/main/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;

        http.formLogin()
                .loginPage("/blog/login") //로그인페이지 주소
                .defaultSuccessUrl("/main/mainPage") // 로그인 성공시 이동주소
                .usernameParameter("id") //유저네임 변수명
                .passwordParameter("password") // 패스워드 변수명
                .failureUrl("/blog/login/error") //로그인 실패시 이동주소
                .loginProcessingUrl("/blog/login")
                .permitAll()
        ;

        http.logout()
                .logoutSuccessUrl("/blog/login")
                .permitAll()
        ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;
        return  http.build();
    }




}