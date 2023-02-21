package com.portfolio.blog.repository;

import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepositoryCustom{

    Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable);
}
