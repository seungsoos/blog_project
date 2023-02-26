package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.entity.BlogInfo;

public interface BlogInfoService{

    BlogInfo findByMember_id(String id);

    //블로그 정보저장
    void saveBlogInfo(BlogInfoDTO blogInfoDTO);

    //블로그 정보수정
    void modifyBlogInfo(BlogInfoDTO blogInfoDTO);
}
