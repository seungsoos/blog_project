package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogBrdListDTO;
import com.portfolio.blog.entity.BlogBrdList;

import java.util.List;

public interface BlogBrdListService {
    List<BlogBrdList> findByMember_Id(String id);
    List<BlogBrdList> findAll();
    void saveBlogBrdList(BlogBrdListDTO blogBrdListDTO);

    BlogBrdList findByCnum(Long cnum);

    void modifyBlogBrdList(BlogBrdListDTO blogBrdListDTO);
}
