package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.BlogPostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostImgService{
    List<BlogPostImg> findByPNum(Long pnum);
}
