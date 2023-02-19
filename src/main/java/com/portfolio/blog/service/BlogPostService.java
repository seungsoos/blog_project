package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> findAll();
}
