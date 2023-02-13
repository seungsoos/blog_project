package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogVisitCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogVisitCountRepository extends JpaRepository<BlogVisitCount, Long> {
}
