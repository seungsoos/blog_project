package com.portfolio.blog.controller;

import com.portfolio.blog.config.SecurityConfig;
import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.MemberDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.BlogInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.jni.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/main")
@RequiredArgsConstructor
public class BlogMainController {

    private final BlogInfoRepository blogInfoRepository;

    @GetMapping("/mainPage")
    public String main(){
        log.info("메인페이지----------------");
        return "main/mainForm";
    }

    //블로그생성
    @GetMapping("/createBlog")
    public String createBlog(Model model){
        model.addAttribute("blogInfoDTO", new BlogInfoDTO());
        model.addAttribute("blogListDTO", new BlogListDTO());
        return "main/createBlogForm";
    }

    //블로그정보
    @PostMapping("/createBlog")
    public String createBlogResult(@Valid BlogInfoDTO blogInfoDTO,
                                   @Valid BlogListDTO blogListDTO,
                                   BindingResult bindingResult,
                                   Model model){
        log.info("블로그 기본정보입력");
        log.info(blogInfoDTO);

        log.info("dto테스트 : " + blogListDTO);


      /*  if (bindingResult.hasErrors()){
            log.info("에러------------발견");
            return "main/createBlogForm";
        }*/

       /* try {
            if (blogLogo != null && blogLogo.isEmpty()){
                log.info("값이 있다.");
            }else{
                log.info("값이 없다.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

        return  "redirect:/main/mainPage";
    }
    


}
