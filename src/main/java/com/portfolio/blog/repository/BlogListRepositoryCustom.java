package com.portfolio.blog.repository;

import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.MemberFriend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogListRepositoryCustom {

    Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable);

    //친구목록
    Page<MemberFriend> getFriendBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable, String loginId);
}
