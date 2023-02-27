package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogMemberVisitCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BlogMemberVisitCountRepository extends JpaRepository<BlogMemberVisitCount, Long> {
    int  countByBlogList_BnumAndRegTimeBetween(Long bnum, LocalDateTime startDateTime, LocalDateTime endDateTime);
    int countByBlogList_Bnum(Long bnum);
}
