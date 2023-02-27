package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogVisitCountDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogVisitCount;
import com.portfolio.blog.repository.BlogVisitCountRepository;
import com.portfolio.blog.service.BlogVisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogVisitCountServiceImpl implements BlogVisitCountService {
    private  final BlogVisitCountRepository blogVisitCountRepository;

    @Override
    public int countByBlogList_BnumAndRegTimeBetween(Long bnum, LocalDateTime startDateTime, LocalDateTime endDateTime){
        return blogVisitCountRepository.countByBlogList_BnumAndRegTimeBetween(bnum, startDateTime, endDateTime);
    }

    @Override
    public int countByBlogList_Bnum(Long bnum) {
        return blogVisitCountRepository.countByBlogList_Bnum(bnum);
    }

    @Override
    public void saveBlogVisitCount(BlogVisitCountDTO blogVisitCountDTO) {
        BlogVisitCount blogVisitCount = blogVisitCountDTO.createBlogMemberVisitCount();
        blogVisitCountRepository.save(blogVisitCount);
    }
}
