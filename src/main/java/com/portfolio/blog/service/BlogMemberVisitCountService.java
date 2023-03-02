package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogMemberVisitCountDTO;
import com.portfolio.blog.dto.MemberFriendDTO;
import com.portfolio.blog.entity.BlogList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BlogMemberVisitCountService {
    int  countByBlogList_BnumAndRegTimeBetween(Long bnum, LocalDateTime startDateTime, LocalDateTime endDateTime);

    int countByBlogList_Bnum(Long bnum);

    void saveBlogMemberVisitCount(BlogMemberVisitCountDTO blogMemberVisitCountDTO);
}
