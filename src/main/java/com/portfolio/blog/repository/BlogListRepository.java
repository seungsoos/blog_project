package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogListRepository extends JpaRepository<BlogList, Long> {
    BlogList findByMember(String id);
}
