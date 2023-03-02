package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogVisitCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BlogVisitCountRepository extends JpaRepository<BlogVisitCount, Long> {

    int countByBlogList_BnumAndRegTimeBetween(Long bnum, LocalDateTime startDateTime, LocalDateTime endDateTime);

    int countByBlogList_Bnum(Long bnum);
}
