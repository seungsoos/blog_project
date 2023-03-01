package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.dto.PostReplyDTO;
import com.portfolio.blog.entity.PostReply;

import java.util.List;

public interface PostReplyService {

    void savePostReply(PostReplyDTO postReplyDTO);

    List<PostReply> findByBlogPost_pnum(Long pnum);
}
