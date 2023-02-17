package com.portfolio.blog.controller;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.MemberDTO;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/main")
@RequiredArgsConstructor
public class BlogMainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogMainController.class);
    private final MemberRepository memberRepository;
    private final BlogListService blogListService;
    private final BlogInfoService blogInfoService;

    @GetMapping("/mainPage")
    public String main(Authentication authentication, HttpSession session){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();
//            LOGGER.info(userDetails.getUsername());

            MemberDTO memberDTO = new MemberDTO();
            Optional<Member> member = memberRepository.findById(id);

        member.ifPresent(value -> memberDTO.setNickName(value.getNickName()));
        member.ifPresent(value -> memberDTO.setId(value.getId()));
        member.ifPresent(value -> memberDTO.setName(value.getName()));
        LOGGER.info(memberDTO+"");
        session.setAttribute("memberDTO", memberDTO);
//        log.info("세션값 확인 : " + memberDTO);
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
                                   @RequestParam(value = "blogLogoImg") List<MultipartFile> blogLogoImg,
                                   @RequestParam("id") Member id,
                                   BindingResult bindingResult,
                                   Model model){

        if (bindingResult.hasErrors()){
            log.info("에러------------발견");
            return "main/createBlogForm";
        }


        blogInfoDTO.setMember(id);
        blogListDTO.setMember(id);

        log.info("blogInfoDTO : " + blogInfoDTO);
        log.info("blogListDTO : " + blogListDTO);

        blogInfoService.saveBlogInfo(blogInfoDTO, blogLogoImg);
        blogListService.saveBlogList(blogListDTO);




        return  "redirect:/main/mainPage";
    }
}