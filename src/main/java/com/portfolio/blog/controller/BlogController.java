package com.portfolio.blog.controller;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.constant.Role;
import com.portfolio.blog.dto.*;
import com.portfolio.blog.entity.*;
import com.portfolio.blog.repository.*;
import com.portfolio.blog.service.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/blog")
@RequiredArgsConstructor
public class    BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
    private final MemberRepository memberRepository;
    private final BlogListService blogListService;
    private final BlogListRepository blogListRepository;
    private final BlogInfoService blogInfoService;
    private final BlogPostService blogPostService;
    private final BlogBrdListRepository blogBrdListRepository;
    private final BlogVisitCountService blogVisitCountService;
    private final BlogMemberVisitCountService blogMemberVisitCountService;
    private final MemberFriendService memberFriendService;
    private final PostReplyService postReplyService;
    private final MemberService memberService;

    @RequestMapping({"/blogMain", "/blogMain/{page}","/blogMain/{page}/{bnum}"})
    public String main(Authentication authentication, HttpSession session, @PathVariable("page") Optional<Integer> page, @PathVariable("bnum") Optional<Integer> bnum, Model model,
                       PostSearchDTO postSearchDTO) {
        String id = null;
        MemberDTO memberDTO = null;
        BlogList blogList = null;
        LocalDateTime date = LocalDateTime.now();

        LocalDateTime startDateTime = date.withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDateTime = date.withHour(23).withMinute(59).withSecond(59).withNano(0);

        int visit, memberVisit, todayTotalVisitCnt, totalVisitCnt, totalVisit, totalMemberVisit;

        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            id = userDetails.getUsername();
            memberDTO = (MemberDTO) session.getAttribute("memberDTO");

            blogList = blogListService.findByMember_id(memberDTO.getId());
        }

        BlogList blogList1 = null;
        //타인이 나의 블로그 접근
        if (bnum.isPresent()) {
            model.addAttribute("bnum", bnum.get());
            blogList1 = blogListService.findByBnum(Long.valueOf(bnum.get()));
            postSearchDTO.setBnum(bnum.get().longValue());
            visit = blogVisitCountService.countByBlogList_BnumAndRegTimeBetween(bnum.get().longValue(), startDateTime, endDateTime);
            memberVisit = blogMemberVisitCountService.countByBlogList_BnumAndRegTimeBetween(bnum.get().longValue(), startDateTime, endDateTime);
            totalVisit = blogMemberVisitCountService.countByBlogList_Bnum(bnum.get().longValue());
            totalMemberVisit = blogVisitCountService.countByBlogList_Bnum(bnum.get().longValue());
        }else{
            //본인이 나의 블로그 접근
            model.addAttribute("bnum", blogList.getBnum());
            blogList1 = blogListService.findByBnum(blogList.getBnum());
            postSearchDTO.setBnum(blogList.getBnum());
            visit = blogVisitCountService.countByBlogList_BnumAndRegTimeBetween(blogList.getBnum(), startDateTime, endDateTime);
            memberVisit = blogMemberVisitCountService.countByBlogList_BnumAndRegTimeBetween(blogList.getBnum(), startDateTime, endDateTime);

            totalVisit = blogMemberVisitCountService.countByBlogList_Bnum(blogList.getBnum());
            totalMemberVisit = blogVisitCountService.countByBlogList_Bnum(blogList.getBnum());
            // 로그인한 유저에게 보내온 친구요청 확인
            List<MemberFriend> friendList = memberFriendService.findByFriendIdAndType(id, FriendShip.STANDBY);
            if(friendList != null){
                // 친구요청한 유저 정보 가져오기
                List<Member> requestMember = new ArrayList<>();
                for(MemberFriend friendRequest : friendList){
                    Optional<Member> memberFriend = memberRepository.findById(friendRequest.getLoginId());
                    requestMember.add(memberFriend.get());
                }
                log.info(requestMember);
                // 로그인한 유저에게 친구신청한 유저 정보
                model.addAttribute("requestMember", requestMember);
            }
        }
        todayTotalVisitCnt = visit + memberVisit;
        model.addAttribute("todayTotalVisitCnt", todayTotalVisitCnt);

        totalVisitCnt = totalVisit + totalMemberVisit;
        model.addAttribute("totalVisitCnt", totalVisitCnt);

        Pageable pageable = PageRequest.of(page.orElse(0), 8);
        Page<BlogPost>  memberBlogList = blogPostService.getMemberBlogPage(postSearchDTO,pageable);

        model.addAttribute("memberBlog", memberBlogList);
        model.addAttribute("postSearchDTO", postSearchDTO);
        model.addAttribute("maxPage", 10);
        model.addAttribute("blogList", blogList1);

        return "blog/blogForm";
    }
    @ResponseBody
    @PostMapping("/replyAccept")
    public ResponseEntity<String> replyAccept(HttpSession session,
                                              @RequestBody HashMap<String, String> map, PostReplyDTO postReplyDTO) {
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        if(session.getAttribute("memberDTO") == null){
           return new ResponseEntity<String>("로그인후 이용해주세요.", resHeaders, HttpStatus.BAD_REQUEST);
       }
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        postReplyDTO.setId(memberDTO.createMember());
        String pnum = map.get("pnum");
        postReplyDTO.setBlogPost(blogPostService.findByPnum(Long.parseLong(pnum)));
        postReplyDTO.setReplyTitle(map.get("replyTitle"));
        postReplyDTO.setReplyText(map.get("replyText"));
        if(map.get("replyAuthority").equals(2)){
        postReplyDTO.setReplyAuthority(Authority.UNLICENSED);
        }else {
        postReplyDTO.setReplyAuthority(Authority.PERMISSION);
        }
        postReplyService.savePostReply(postReplyDTO);
        return new ResponseEntity<String> ("댓글쓰기 성공", resHeaders, HttpStatus.OK);
    }
    @RequestMapping("/blogView/{bnum}/{pnum}")
    public String blogView( @PathVariable("pnum") Optional<Integer> pnum, @PathVariable("bnum") Optional<Integer> bnum,
                            Model model, PostSearchDTO postSearchDTO, HttpSession session, PostReplyDTO postReplyDTO){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        if(memberDTO != null){
            model.addAttribute("loginId", memberDTO.getId());
        }else{
            model.addAttribute("loginId", null);
        }
        if (pnum.isPresent()) {
            List<PostReply> postReplyList = postReplyService.findByBlogPost_pnum(pnum.get().longValue());
            model.addAttribute("postReplyList", postReplyList);
            BlogPost blogPost = blogPostService.findByPnum(pnum.get().longValue());
            model.addAttribute("BlogPost", blogPost);
            // 최근 게시글 8개만 표시
            Pageable pageable = PageRequest.of(0, 8);
            postSearchDTO.setBnum(blogPost.getBlogList().getBnum());
            Page<BlogPost>  postList = blogPostService.getMemberBlogPage(postSearchDTO,pageable);
            model.addAttribute("postList", postList);
            model.addAttribute("postReply", new PostReplyDTO());

            return  "blog/blogView";

        }else {
            return "redirect:/blog/blogForm";
        }
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
        Member member = new Member();

        log.info("blogInfoDTO : " + blogInfoDTO);
        log.info("blogListDTO : " + blogListDTO);

        blogInfoService.saveBlogInfo(blogInfoDTO);
        String loginId = id.getId();

        memberService.updateMemberRole(loginId);

        Long bnum = blogListRepository.save(blogListDTO.createBlogList()).getBnum();
        return  "redirect:/blog/blogMain/0/"+bnum;
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

//      blogPost 저장, blogBrdPost (읽기, 댓글쓰기 권한) 저장
        blogBrdListDTO.setId(id);
        BlogBrdList blogBrdList = blogBrdListDTO.createBlogBrdList(); // DTO -> 엔티티
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

    //게시글수정
    @GetMapping("/postModify{pnum}")
    public String postModifyPage(HttpSession session, Model model, @PathVariable("pnum") Optional<Integer> pnum){

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String id =memberDTO.getId();

        if(pnum.isPresent()){
            BlogPost blogPost =  blogPostService.findByPnum(pnum.get().longValue());
            BlogPostDTO blogPostDTO = BlogPostDTO.of(blogPost);

            model.addAttribute("blogPostDTO", blogPostDTO);
        }
        return "blog/postModifyForm";
    }

    //게시글수정
    @PostMapping("/postModify/{pnum}")
    public String postModify(HttpSession session, Model model, @PathVariable("pnum") Optional<Integer> pnum, @Valid BlogPostDTO blogPostDTO){
        BlogBrdList blogBrdList =  blogPostDTO.getBlogBrdList();
        BlogPost blogPost = blogPostService.findByPnum(pnum.get().longValue());
        blogPostDTO.setId(blogPost.getMember());
        blogPostDTO.setBlogList(blogPost.getBlogList());
        blogPostService.modifyBlogPost(blogPostDTO);
        return "redirect:/blog/blogMain";
    }
    //친구요청 Ajax
    @ResponseBody
    @PostMapping("/friendRequest")
    public ResponseEntity<String> friendRequest(HttpSession session,
                                                @RequestBody HashMap<String, String> memberFriend) {
            HttpHeaders resHeaders = new HttpHeaders();
            resHeaders.add("Content-Type", "application/json;charset=UTF-8");
            if(session.getAttribute("memberDTO") == null){
                return new ResponseEntity<String>("로그인후 이용해주세요.", resHeaders, HttpStatus.BAD_REQUEST);
             }
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
        //블로그 수정
        @PostMapping("/blogModify")
        public String blogModify (@Valid BlogInfoDTO blogInfoDTO,
                @Valid BlogListDTO blogListDTO,
                @RequestParam("member") Member id,
                BindingResult bindingResult,
                Model model){

            if (bindingResult.hasErrors()) {
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


        //방문자수 count
        @ResponseBody
        @PostMapping("/blogVisitRequest")
        public void blogVisitRequest (HttpSession session, @RequestBody HashMap < String, String > map){
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
            BlogList blogList = blogListService.findByBnum(Long.parseLong(map.get("bnum")));
            log.info(blogList);
            if (memberDTO != null) {
                BlogMemberVisitCountDTO blogMemberVisitCountDTO = new BlogMemberVisitCountDTO();
                blogMemberVisitCountDTO.setId(memberDTO.createMember());
                blogMemberVisitCountDTO.setBlogList(blogList);
                blogMemberVisitCountService.saveBlogMemberVisitCount(blogMemberVisitCountDTO);
            } else {
                BlogVisitCountDTO blogVisitCountDTO = new BlogVisitCountDTO();
                blogVisitCountDTO.setBlogList(blogList);
                blogVisitCountService.saveBlogVisitCount(blogVisitCountDTO);
            }

        }

        //전체블로그 목록
        @RequestMapping({"/memberBlogList", "/memberBlogList/{page}"})
        public String memberBlogList(HttpSession session, @PathVariable("page") Optional<Integer> page, Model model,
                                     BlogSearchDTO blogSearchDTO, Authentication authentication){
            List<String> friendList = new ArrayList<>();
        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String id = userDetails.getUsername();
            MemberDTO memberDTO = new MemberDTO();
            Optional<Member> member = memberRepository.findById(id);
            member.ifPresent(value -> memberDTO.setNickName(value.getNickName()));
            member.ifPresent(value -> memberDTO.setId(value.getId()));
            member.ifPresent(value -> memberDTO.setName(value.getName()));
            session.setAttribute("memberDTO", memberDTO);
            log.info("*--------------"+ memberDTO);

            if(memberDTO != null && blogListService.findByMember_id(memberDTO.getId()) != null){
                BlogList blogList =  blogListService.findByMember_id(memberDTO.getId());
                blogSearchDTO.setBnum(blogList.getBnum());
                // 로그인 유저가 친구신청 보낸 정보
                List<MemberFriend> loginRelationshipList = memberFriendService.findByLoginId(memberDTO.getId());
                // 로그인 유저가 친구신청 받은 정보
                List<MemberFriend> memberRelationshipList = memberFriendService.findByFriendId(memberDTO.getId());
                // 로그인유저 기준으로 이미 친구 관계인 유저 뽑아내기

                for(MemberFriend friend :memberRelationshipList){
                    // 친구관계일때 로그인유저가 친구신청 받은 경우 친구신청 보낸 유저아이디 찾기
                    if(friend.getType() == FriendShip.FRIENDS){
                        friendList.add(friend.getLoginId());
                    }
                }
                for(MemberFriend friend : loginRelationshipList){
                    // 친구관계일때 로그인유저가 친구신청 보낸 경우 친구신청 받은 유저아이디 찾기
                    if(friend.getType() == FriendShip.FRIENDS){
                        friendList.add(friend.getFriendId());
                    }
                }
                log.info(friendList);
            }
        }


            Pageable pageable = PageRequest.of(page.orElse(0), 8);
            Page<BlogList>  memberBlogList = blogListService.getMemberBlogPage(blogSearchDTO,pageable);


            log.info(friendList+"----------------------");
            model.addAttribute("memberBlog", memberBlogList);
            model.addAttribute("blogSearchDTO", blogSearchDTO);
            model.addAttribute("maxPage", 10);
            model.addAttribute("friendList", friendList);

            return "blog/memberBlogForm";
        }

        //친구블로그 목록
        @RequestMapping({"/friendBlogList", "/friendBlogList/{page}"})
        public String friendBlogList (HttpSession session, @PathVariable("page") Optional < Integer > page, Model model,
                BlogSearchDTO blogSearchDTO){
            Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 8);
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
            String loginId = memberDTO.getId();

            BlogList blogList = blogListService.findByMember_id(memberDTO.getId());
            
            //로그인
            if(blogList != null){
            blogSearchDTO.setBnum(blogList.getBnum());
            }

            BlogList blogList1;
            Page<MemberFriend> friendBlogList = blogListService.getFriendBlogPage(blogSearchDTO, pageable, loginId);
            log.info(friendBlogList+"111111111111111111111111");
            List<BlogList> friendInfo = new ArrayList<>();
            for (int i = 0; i < friendBlogList.getContent().size(); i++) {
                String friendId;
                if (friendBlogList.getContent().get(i).getFriendId().equals(loginId)) {
                    friendId = friendBlogList.getContent().get(i).getLoginId();
                } else {
                    friendId = friendBlogList.getContent().get(i).getFriendId();
                }
                log.info(friendId+"22222222222222222");
                blogList1 = blogListService.findByMember_id(friendId);

                friendInfo.add(blogList1);
            }
            log.info(friendInfo);
            if(friendInfo.contains(null)){
                log.info("111111111111111111111111111");
                model.addAttribute("friendInfo","nothing");
            }else{
                log.info("222222222222222222222222");
                model.addAttribute("friendInfo", friendInfo);
            }
            model.addAttribute("memberBlog", friendBlogList);

            model.addAttribute("blogSearchDTO", blogSearchDTO);
            model.addAttribute("maxPage", 10);

            return "blog/friendBlogForm";
        }

    //친구삭제 Ajax1
    @ResponseBody
    @DeleteMapping("/friendDelete")
    public ResponseEntity<String> friendDelete (@RequestBody HashMap < String, Long > friend){
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        Long fnum = friend.get("fnum");
        log.info(fnum);

        if(fnum != null){
            memberFriendService.deleteByFnum(fnum);
            return new ResponseEntity<String>("친구삭제가 완료되었습니다.", resHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<String>("오류발생", resHeaders, HttpStatus.BAD_REQUEST);
    }

        //친구신청 수락
        @ResponseBody
        @PutMapping("/friendAccept")
        public ResponseEntity<String> friendAccept (HttpSession session,
                @RequestBody HashMap < String, String > map){
            HttpHeaders resHeaders = new HttpHeaders();
            resHeaders.add("Content-Type", "application/json;charset=UTF-8");

            MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
            String loginId = memberDTO.getId();
            String friendId = map.get("friendId");
            if (friendId != null) {
                memberFriendService.updateMemberFriend(loginId, friendId);
                return new ResponseEntity<String>("친구신청이 수락 되었습니다.", resHeaders, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("오류발생", resHeaders, HttpStatus.BAD_REQUEST);
            }

        }

        //친구신청 거절
        @ResponseBody
        @DeleteMapping("/friendRefuse")
        public ResponseEntity<String> friendRefuse (HttpSession session,
                @RequestBody HashMap < String, String > map){
            HttpHeaders resHeaders = new HttpHeaders();
            resHeaders.add("Content-Type", "application/json;charset=UTF-8");
            MemberDTO memberDTO = (MemberDTO)session.getAttribute("memberDTO");
            String loginId = memberDTO.getId();
            String friendId = map.get("friendId");
            MemberFriend memberFriend = memberFriendService.findByLoginIdAndFriendId(loginId, friendId);
            MemberFriend memberFriend1 = memberFriendService.findByLoginIdAndFriendId(friendId,loginId);

            if (memberFriend != null) {
                memberFriendService.deleteByFnum(memberFriend.getFnum());
                return new ResponseEntity<String>("친구삭제가 완료되었습니다.", resHeaders, HttpStatus.OK);
            } else if(memberFriend1 != null){
                memberFriendService.deleteByFnum(memberFriend1.getFnum());
                return new ResponseEntity<String>("친구삭제가 완료되었습니다.", resHeaders, HttpStatus.OK);
            }
            return new ResponseEntity<String>("오류발생", resHeaders, HttpStatus.BAD_REQUEST);
        }
    }