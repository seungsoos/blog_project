package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.repository.BlogListRepository;
import com.portfolio.blog.service.BlogListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogListServiceImpl implements BlogListService {

    private  final BlogListRepository blogListRepository;

    @Override
    public BlogList findByMember(String id) {
        return blogListRepository.findByMember(id);
    }
    
    //블로그 정보저장
    @Override
    public void saveBlogList(BlogListDTO blogListDTO) {
        BlogList bloglist = blogListDTO.saveBlogList();
        blogListRepository.save(bloglist);
    }
}
