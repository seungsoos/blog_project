package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogVisitCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BlogVisitCountRepository extends JpaRepository<BlogVisitCount, Long> {

    int findCountByRegTime(LocalDateTime regTime);
}
