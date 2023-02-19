package com.portfolio.blog.service;

import java.time.LocalDateTime;

public interface BlogMemberVisitCountService {
    int  findCountByRegTime(LocalDateTime regTime);
}
