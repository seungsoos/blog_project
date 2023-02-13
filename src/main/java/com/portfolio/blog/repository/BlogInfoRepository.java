package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long> {
}
