package com.portfolio.blog.controller;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.repository.BlogInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@Log4j2
@RequestMapping("/main")
@RequiredArgsConstructor
public class BlogMainController {

    private final BlogInfoRepository blogInfoRepository;

    @GetMapping("/mainPage")
    public String main(){

        return "main/mainForm";
    }

    //블로그생성페이지 이동
    @GetMapping("/createBlog")
    public String createBlog(Model model){
        model.addAttribute("blogInfoDTO", new BlogInfoDTO());
        System.out.println("GetCreateBlog---------------------------");
        System.out.println("한글테스트1");
        return "main/createBlogForm";
    }

    //블로그 생성처리
    @PostMapping("/createBlog")
    public String createBlogResult(@Valid BlogInfoDTO blogInfoDTO,
                                   BindingResult bindingResult,
                                   @RequestParam("blogLogo") MultipartFile blogLogo,
                                   Model model){
        System.out.println("PostCreateBlog---------------------------");

        System.out.println(blogInfoDTO);

        if (bindingResult.hasErrors()){
            System.out.println("에러------------발견");
            return "main/createBlogForm";
        }
        try {
            if (blogLogo != null && blogLogo.isEmpty()){
                log.info("값이 있다.");
            }else{
                log.info("값이 없다.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  "main/mainPageForm";
    }
}
