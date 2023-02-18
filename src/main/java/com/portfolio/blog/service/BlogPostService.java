package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostService{
    List<BlogPost> findAll();
}
