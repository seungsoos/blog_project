package com.portfolio.blog.repository;

import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.BlogList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogListRepositoryCostom {

    Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable);
}
