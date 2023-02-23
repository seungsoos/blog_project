package com.portfolio.blog.controller;

import com.portfolio.blog.dto.*;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.*;
import com.portfolio.blog.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.hibernate.engine.jdbc.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
    private final MemberRepository memberRepository;
    private final BlogListService blogListService;
    private final BlogInfoService blogInfoService;
    private  final BlogPostService blogPostService;
    private  final BlogPostRepository blogPostRepository;
    private  final BlogPostRepositoryCustom blogPostRepositoryCustom;
    private  final BlogBrdListService blogBrdListService;
    private  final BlogBrdListRepository blogBrdListRepository;
    private  final BlogListRepository blogListRepository;

    private  final MemberFriendService memberFriendService;

    @RequestMapping({"/blogMain", "/blogMain/{page}", "/blogMain/{bnum}"})
    public String main(Authentication authentication, HttpSession session, @PathVariable("page") Optional<Integer> page, @PathVariable("bnum") Optional<Integer> bnum, Model model,
                       PostSearchDTO postSearchDTO) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();

        MemberDTO memberDTO = new MemberDTO();
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(value -> memberDTO.setNickName(value.getNickName()));
        member.ifPresent(value -> memberDTO.setId(value.getId()));
        member.ifPresent(value -> memberDTO.setName(value.getName()));

        session.setAttribute("memberDTO", memberDTO);
        if(blogListService.findByMember_id(memberDTO.getId()) == null){
            return "redirect:/blog/blogCreate";
        }
        BlogList blogList = blogListService.findByMember_id(memberDTO.getId());
        if (bnum.isPresent()) {
            postSearchDTO.setBnum(bnum.get().longValue());
        } else {
            postSearchDTO.setBnum(blogList.getBnum());
        }

        Pageable pageable = PageRequest.of(page.orElse(0), 8);
        Page<BlogPost>  memberBlogList = blogPostService.getMemberBlogPage(postSearchDTO,pageable);

        model.addAttribute("memberBlog", memberBlogList);
        model.addAttribute("postSearchDTO", postSearchDTO);
        model.addAttribute("maxPage", 10);

        return "blog/blogForm";
    }

    @RequestMapping("/blogView/{bnum}")
    public String blogView( @PathVariable("bnum") Optional<Integer> bnum, Model model, PostSearchDTO postSearchDTO, HttpSession session){
        Pageable pageable = PageRequest.of(0, 8);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        BlogList blogList = blogListService.findByMember_id(memberDTO.getId());
        if (bnum.isPresent()) {
            postSearchDTO.setBnum(bnum.get().longValue());
        }else {
            postSearchDTO.setBnum(blogList.getBnum());
        }
        Page<BlogPost>  memberBlogList = blogPostService.getMemberBlogPage(postSearchDTO,pageable);

        BlogPost blogPost = blogPostService.findByBlogList_Bnum(bnum.get().longValue());

        model.addAttribute("BlogPost", blogPost);
        model.addAttribute("memberBlogList", memberBlogList);
        return  "blog/blogView";
    }


    //블로그생성
    @GetMapping("/blogCreate")
    public String createBlog(Model model) {
        model.addAttribute("blogInfoDTO", new BlogInfoDTO());
        model.addAttribute("blogListDTO", new BlogListDTO());
        return "blog/createBlogForm";
    }

    //블로그생성
    @PostMapping("/blogCreate")
    public String createBlogResult(@Valid BlogInfoDTO blogInfoDTO,
                                   @Valid BlogListDTO blogListDTO,
                                   @RequestParam("member") Member id,
                                   BindingResult bindingResult,
                                   Model model) {

        if (bindingResult.hasErrors()) {
            log.info("에러------------발견");
            return "blog/createBlogForm";
        }
        log.info("*--------------" + id);
        blogInfoDTO.setId(id);
        blogListDTO.setId(id);

        log.info("blogInfoDTO : " + blogInfoDTO);
        log.info("blogListDTO : " + blogListDTO);

        blogInfoService.saveBlogInfo(blogInfoDTO);
        blogListService.saveBlogList(blogListDTO);

        return  "redirect:/blog/blogMain";
    }

    //게시글 생성
    @GetMapping("/postCreate")
    public String createPost(Model model){
        model.addAttribute("blogPostDTO", new BlogPostDTO());
        model.addAttribute("blogBrdListDTO", new BlogBrdListDTO());
        return "blog/createPostForm";
    }
    //게시글 생성
    @PostMapping("/postCreate")
    public String createPost(@Valid BlogPostDTO blogPostDTO,
                             @Valid BlogBrdListDTO blogBrdListDTO,
                             @RequestParam("member") Member id){
        log.info(blogBrdListDTO);

        log.info(blogPostDTO.getPostText());

//      blogPost 저장, blogBrdPost (읽기, 댓글쓰기 권한) 저장, 후에 blogPostImg 저장
        BlogBrdList blogBrdList=  blogBrdListDTO.createBlogBrdList(); // DTO -> 엔티티
        Long cnum = blogBrdListRepository.save(blogBrdList).getCnum(); // 저장되면서 만들어진 cnum 가져오기
        BlogList blogList = blogListService.findByMember_id(id.getId());

        blogBrdList.setCnum(cnum); // cnum과 일치하는 엔티티 값을 저장
        blogPostDTO.setBlogList(blogList); //  BlogList forinKey 저장
        blogPostDTO.setId(id); // 파라미터로 가져온 id값
        blogPostDTO.setBlogBrdList(blogBrdList);
        blogPostService.saveBlogPost(blogPostDTO);

        // blogPostImg blogPost가 foreign key로 들어감
        return  "redirect:/blog/blogMain";
    }

    //블로그 수정
    @GetMapping("/blogModify")
    public String blogModify(HttpSession session, Model model){

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String id =memberDTO.getId();

        BlogInfoDTO blogInfoDTO = BlogInfoDTO.of(blogInfoService.findByMember_id(id));
        log.info("blogInfoDTO : " + blogInfoDTO);
        BlogListDTO blogListDTO = BlogListDTO.of(blogListService.findByMember_id(id));
        log.info("blogListDTO : " + blogListDTO);

        model.addAttribute("blogInfoDTO", blogInfoDTO);
        model.addAttribute("blogListDTO", blogListDTO);
        return "blog/blogModifyForm";
    }
    //친구요청 Ajax
    @ResponseBody
    @PostMapping("/friendRequest")
    public void friendRequest(HttpSession session,
                              @RequestBody HashMap<String, String> memberFriend){

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");

        String friendId = memberFriend.get("friendId");

        //친구 추가에서 친구 정보 가져오기
        Optional<Member> friend =memberRepository.findById(friendId);

        //친구 추가 작업하기
        MemberFriendDTO memberFriendDTO = new MemberFriendDTO();

        memberFriendDTO.setFriendId(friend.get().getId());
        memberFriendDTO.setLoginId(memberDTO.getId());
        log.info(memberFriendDTO);

        memberFriendService.saveFriendList(memberFriendDTO);
    }
    @RequestMapping({"/memberBlogList", "/memberBlogList/{page}"})
    public String friendBlogList(HttpSession session, @PathVariable("page") Optional<Integer> page, Model model,
    BlogSearchDTO blogSearchDTO){
        log.info(blogSearchDTO);

        MemberDTO memberDTO =  (MemberDTO) session.getAttribute("memberDTO");
        BlogList blogList =  blogListService.findByMember_id(memberDTO.getId());

        blogSearchDTO.setBnum(blogList.getBnum());
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 8);
        Page<BlogList>  memberBlogList = blogListService.getMemberBlogPage(blogSearchDTO,pageable);

        model.addAttribute("memberBlog", memberBlogList);
        model.addAttribute("blogSearchDTO", blogSearchDTO);
        model.addAttribute("maxPage", 10);

        return "blog/memberBlogForm";
    }

    @PostMapping("/blogModify")
    public String blogModify(@Valid BlogInfoDTO blogInfoDTO,
                             @Valid BlogListDTO blogListDTO,
                             @RequestParam("member") Member id,
                             BindingResult bindingResult,
                             Model model){

        if (bindingResult.hasErrors()){
            log.info("에러------------발견");
            return "blog/blogModifyForm";
        }

        log.info(id);

        log.info(blogInfoDTO);
        log.info(blogListDTO);
        blogInfoDTO.setId(id);
        blogListDTO.setId(id);
        blogInfoService.modifyBlogInfo(blogInfoDTO);
        blogListService.modifyBlogList(blogListDTO);

        return "redirect:/blog/blogMain";
    }

}