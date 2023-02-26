package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.MemberFriend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogListService {
    BlogList findByMember_id(String id);

    //블로그 정보저장
    void saveBlogList(BlogListDTO blogListDTO);

    //블로그 정보수정
    void modifyBlogList(BlogListDTO blogListDTO);

    Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable);

    Page<MemberFriend> getFriendBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable, String loginId);
}
