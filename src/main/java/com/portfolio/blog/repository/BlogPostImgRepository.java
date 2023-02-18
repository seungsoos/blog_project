package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogPostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostImgRepository extends JpaRepository<BlogPostImg, Long> {
    List<BlogPostImg> findByBlogPost_pnum(Long pnum);
}
