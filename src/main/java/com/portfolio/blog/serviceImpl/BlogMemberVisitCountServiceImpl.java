package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.service.BlogVisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogMemberVisitCountServiceImpl implements BlogVisitCountService {
    private  final  BlogVisitCountService blogVisitCountService;

    @Override
    public int CountByVisitDate(String VisitDate) {
        return blogVisitCountService.CountByVisitDate(VisitDate);
    }
}
