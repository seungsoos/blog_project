package com.portfolio.blog.repository;

import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogPostRepositoryCustom{

    Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable);
}
