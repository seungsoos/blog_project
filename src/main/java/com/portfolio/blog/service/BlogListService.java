package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.entity.BlogList;

public interface BlogListService {

    BlogList findByMember_id(String id);

    //블로그 정보저장
    void saveBlogList(BlogListDTO blogListDTO);
}
