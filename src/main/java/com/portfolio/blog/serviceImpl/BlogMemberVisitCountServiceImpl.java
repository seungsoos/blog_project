package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogVisitCountDTO;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.BlogVisitCount;
import com.portfolio.blog.repository.BlogVisitCountRepository;
import com.portfolio.blog.service.BlogVisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogMemberVisitCountServiceImpl implements BlogVisitCountService {
    private  final BlogVisitCountRepository blogVisitCountRepository;

    @Override
    public int CountByRegTime(LocalDateTime regTime) {
        return blogVisitCountRepository.findCountByRegTime(regTime);
    }

    @Override
    public void saveBlogVisitCount(BlogVisitCountDTO blogVisitCountDTO) {
        BlogVisitCount blogVisitCount = blogVisitCountDTO.createBlogMemberVisitCount();
        blogVisitCountRepository.save(blogVisitCount);
    }
}
