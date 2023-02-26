package com.portfolio.blog.controller;

import com.portfolio.blog.dto.*;
import com.portfolio.blog.entity.*;
import com.portfolio.blog.repository.*;
import com.portfolio.blog.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.hibernate.engine.jdbc.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
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

    //특정 게시글 보기
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

    //블로그 수정
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

    //전체블로그 목록
    @RequestMapping({"/memberBlogList", "/memberBlogList/{page}"})
    public String memberBlogList(HttpSession session, @PathVariable("page") Optional<Integer> page, Model model,
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

    //친구블로그 목록
    @RequestMapping({"/friendBlogList", "/friendBlogList/{page}"})
    public String friendBlogList(HttpSession session, @PathVariable("page") Optional<Integer> page, Model model,
                                 BlogSearchDTO blogSearchDTO){
        log.info("친구목록-------------------");
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0, 8);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String loginId =memberDTO.getId();

        BlogList blogList = new BlogList();
        blogList =  blogListService.findByMember_id(memberDTO.getId());
        blogSearchDTO.setBnum(blogList.getBnum());
        Page<MemberFriend> friendBlogList = blogListService.getFriendBlogPage(blogSearchDTO,pageable,loginId);
        log.info(friendBlogList.getContent());

        List<BlogList> friendInfo = new ArrayList<>();
        for (int i=0; i<friendBlogList.getContent().size(); i++){

            String friendId = friendBlogList.getContent().get(i).getFriendId();
            log.info(friendId);
            blogList =  blogListService.findByMember_id(friendId);
            friendInfo.add(blogList);

        }
        log.info(friendInfo);

        model.addAttribute("memberBlog", friendBlogList);
        model.addAttribute("friendInfo", friendInfo);
        model.addAttribute("blogSearchDTO", blogSearchDTO);
        model.addAttribute("maxPage", 10);

        return "blog/friendBlogForm";
    }


    //친구요청 Ajax
    @ResponseBody
    @PostMapping("/friendRequest")
    public ResponseEntity<String> friendRequest(HttpSession session,
                                                @RequestBody HashMap<String, String> memberFriend){

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String loginId = memberDTO.getId();
        String friendId = memberFriend.get("friendId");

        // 로그인 유저와 일반 유저가 이미 친구요청을 했다면, 저장 안함
        if(memberFriendService.countByLoginIdAndFriendId(loginId, friendId) < 1 && memberFriendService.countByLoginIdAndFriendId(friendId, loginId) < 1){
            //친구 추가 작업하기
            MemberFriendDTO memberFriendDTO = new MemberFriendDTO();
            memberFriendDTO.setFriendId(friendId);
            memberFriendDTO.setLoginId(loginId);
            log.info(memberFriendDTO);
            memberFriendService.saveFriendList(memberFriendDTO);
            return new ResponseEntity<String>("친구신청이 완료되었습니다.", resHeaders, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("이미 신청한 유저입니다.", resHeaders, HttpStatus.BAD_REQUEST);
        }
    }


    //친구삭제 Ajax
    @ResponseBody
    @PostMapping("/friendDelete")
    public void friendDelete(HttpSession session,
                             @RequestBody HashMap<String, String> memberFriend,
                             MemberDTO memberDTO){
        log.info("친구삭제 --------------------");
        MemberDTO member = (MemberDTO) session.getAttribute("memberDTO");
        Member login = member.createMember();
        String friend =  memberFriend.get("friendId");

        log.info("login : " + login);
        log.info("friend : " + friend);
        memberDTO.setId(friend);
        Member friendId = memberDTO.createMember();

        MemberFriendDTO memberFriendDTO = new MemberFriendDTO();
        memberFriendDTO.setLoginId(login.getId());
        memberFriendDTO.setFriendId(friendId.getId());
        log.info(memberFriendDTO);
        memberFriendService.deleteFriendList(memberFriendDTO);
    }
}