package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.service.BlogVisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogVisitCountServiceImpl implements BlogVisitCountService {
    private  final  BlogVisitCountService blogVisitCountService;

    @Override
    public int CountByVisitDate(String VisitDate) {
        return blogVisitCountService.CountByVisitDate(VisitDate);
    }
}
