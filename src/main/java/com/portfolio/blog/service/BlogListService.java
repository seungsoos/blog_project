package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogList;

public interface BlogListService {
    BlogList findByMember(String id);
}
