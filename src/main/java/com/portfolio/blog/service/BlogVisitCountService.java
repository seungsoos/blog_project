package com.portfolio.blog.service;

import java.time.LocalDateTime;

public interface BlogVisitCountService {
    int  CountByRegTime(LocalDateTime regTime);
}
