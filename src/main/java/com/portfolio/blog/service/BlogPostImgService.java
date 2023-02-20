package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogMemberVisitCountDTO;
import com.portfolio.blog.dto.BlogPostImgDTO;
import com.portfolio.blog.entity.BlogPostImg;

import java.util.List;

public interface BlogPostImgService {
    List<BlogPostImg> findByPNum(Long pnum);

    void saveBlogPostImg(BlogPostImgDTO blogPostImgDTO);

}
