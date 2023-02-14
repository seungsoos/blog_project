package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.repository.BlogVisitCountRepository;
import com.portfolio.blog.service.BlogVisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogVisitCountServiceImpl implements BlogVisitCountService {
    private  final BlogVisitCountRepository blogVisitCountRepository;

    @Override
    public int CountByRegTime(LocalDateTime regTime) {
        return blogVisitCountRepository.findCountByRegTime(regTime);
    }


}
