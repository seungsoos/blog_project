package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.entity.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> findAll();

    void saveBlogPost(BlogPostDTO blogPostDTO);
}
