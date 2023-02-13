package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogInfo;

public interface BlogInfoService{
    BlogInfo findByMember(String id);
}
