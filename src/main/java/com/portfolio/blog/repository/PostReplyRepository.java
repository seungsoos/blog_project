package com.portfolio.blog.repository;

import com.portfolio.blog.entity.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReplyRepository extends JpaRepository<PostReply, Long> {

    List<PostReply> findByBlogPost_pnum(Long pnum);
}
