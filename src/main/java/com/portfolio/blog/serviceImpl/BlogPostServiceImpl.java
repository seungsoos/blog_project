package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.repository.BlogPostRepository;
import com.portfolio.blog.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
