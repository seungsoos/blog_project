package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogMemberVisitCountDTO;

import java.time.LocalDateTime;

public interface BlogMemberVisitCountService {
    int  findCountByRegTime(LocalDateTime regTime);

    void saveBlogMemberVisitCount(BlogMemberVisitCountDTO blogMemberVisitCountDTO);
}
