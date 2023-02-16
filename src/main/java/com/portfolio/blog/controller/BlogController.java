package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MemberDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/blog")
@Log4j2
@RequiredArgsConstructor
public class BlogController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        log.info("login-------------------");
        return "/login/loginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/login/loginForm";
    }

    @GetMapping("/agree")
    public String agree(){
        log.info("agree-------------------");
        return "/login/agreeForm";
    }

    @GetMapping("/join")
    public String join(Model model){
        log.info("Getjoin-------------------");
        model.addAttribute("memberDTO", new MemberDTO());
        return "/login/joinForm";
    }

    @PostMapping(value = "/join")
        public String newMember(@Valid MemberDTO memberDTO,
                BindingResult bindingResult,
                Model model){
            if(bindingResult.hasErrors()){
                return "login/joinForm";
            }
            try {
                Member member = Member.createMember(memberDTO, passwordEncoder);
                memberService.saveMember(member);
            } catch (IllegalStateException e){
                model.addAttribute("errorMessage", e.getMessage());
                return "login/joinForm";
            }

            return "login/loginForm";
    }

    @GetMapping("/logout")
    public String logout(){

        return "/login/loginForm";
    }

    @GetMapping("/idSearch")
    public String idSearch(Model model){
        model.addAttribute("MemberDTO",new MemberDTO());
        return "/login/idSearch";
    }

    @GetMapping("pwdSearch")
    public String pwdSearch(Model model){

        model.addAttribute("MemberDTO",new MemberDTO());
        return "/login/pwdSearch";
    }


}
