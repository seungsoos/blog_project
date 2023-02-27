package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> findByMember_Id(String id);

    BlogPost findByBlogList_Bnum(Long bnum);

    void modifyBlogPost(BlogPostDTO blogPostDTO);

    BlogPost findByPnum(Long pnum);
    void saveBlogPost(BlogPostDTO blogPostDTO);

    Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable);
}
