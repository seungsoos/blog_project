package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long> {

    BlogInfo findByMember_id(String id);


}
