package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.repository.BlogInfoRepository;
import com.portfolio.blog.service.BlogInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogInfoServiceImpl implements BlogInfoService {
    private  final BlogInfoRepository blogInfoRepository;

    @Override
    public BlogInfo findByMember(String id) {
        return blogInfoRepository.findByMember(id);
    }
}
