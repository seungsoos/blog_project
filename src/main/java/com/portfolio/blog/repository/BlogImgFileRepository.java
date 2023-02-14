package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogImgFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogImgFileRepository extends JpaRepository<BlogImgFile, Long> {
    BlogImgFile findByMember (String id);
}
