package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogBrdList;

import java.util.List;

public interface BlogBrdListService {
    List<BlogBrdList> findByMember(String id);
}
