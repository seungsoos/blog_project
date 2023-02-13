package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogMemberVisitCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogMemberVisitCountRepository extends JpaRepository<BlogMemberVisitCount, Long> {

}
