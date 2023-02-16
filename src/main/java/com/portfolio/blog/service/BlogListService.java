package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.entity.BlogList;

public interface BlogListService {
    BlogList findByMember(String id);

    //블로그 정보저장
    public void saveBlogList(BlogListDTO blogListDTO);
}
