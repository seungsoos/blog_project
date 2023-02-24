package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogVisitCountDTO;
import com.portfolio.blog.entity.BlogList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BlogVisitCountService {
    int  countByBlogList_BnumAndRegTimeBetween(Long bnum, LocalDateTime startDateTime, LocalDateTime endDateTime);

    int countBy();

    void saveBlogVisitCount(BlogVisitCountDTO blogVisitCountDTO);
}
