package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogMemberVisitCountDTO;
import com.portfolio.blog.dto.BlogVisitCountDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogMemberVisitCount;
import com.portfolio.blog.entity.BlogVisitCount;
import com.portfolio.blog.repository.BlogMemberVisitCountRepository;
import com.portfolio.blog.repository.BlogVisitCountRepository;
import com.portfolio.blog.service.BlogMemberVisitCountService;
import com.portfolio.blog.service.BlogVisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogMemberVisitCountServiceImpl implements BlogMemberVisitCountService{
    private  final BlogMemberVisitCountRepository blogMemberVisitCountRepository;

    @Override
    public int countByBlogList_BnumAndRegTimeBetween(Long bnum, LocalDateTime startDateTime, LocalDateTime endDateTime){
        return blogMemberVisitCountRepository.countByBlogList_BnumAndRegTimeBetween(bnum, startDateTime, endDateTime);
    }


    @Override
    public int countBy() {
        return blogMemberVisitCountRepository.countBy();
    }

    @Override
    public void saveBlogMemberVisitCount(BlogMemberVisitCountDTO blogMemberVisitCountDTO){
        BlogMemberVisitCount blogMemberVisitCount = blogMemberVisitCountDTO.createBlogMemberVisitCount();
        blogMemberVisitCountRepository.save(blogMemberVisitCount);
    }

}
