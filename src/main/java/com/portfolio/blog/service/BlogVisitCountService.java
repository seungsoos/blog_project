package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogVisitCountDTO;

import java.time.LocalDateTime;

public interface BlogVisitCountService {
    int  CountByRegTime(LocalDateTime regTime);

    void saveBlogVisitCount(BlogVisitCountDTO blogVisitCountDTO);
}
