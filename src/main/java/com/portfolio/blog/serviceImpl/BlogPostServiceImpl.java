package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.repository.BlogPostRepository;
import com.portfolio.blog.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;
    @Override
    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    @Override
    public void saveBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostDTO.createBlogPost();
        blogPostRepository.save(blogPost);
    }

    @Transactional(readOnly = true)
    public Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable){
        return blogPostRepository.getMemberBlogPage(postSearchDTO, pageable);
    }
}
