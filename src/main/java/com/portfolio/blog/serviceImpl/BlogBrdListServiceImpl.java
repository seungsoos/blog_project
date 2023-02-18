package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.repository.BlogBrdListRepository;
import com.portfolio.blog.service.BlogBrdListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogBrdListServiceImpl implements BlogBrdListService {
    private  final BlogBrdListRepository blogBrdListRepository;

    @Override
    public List<BlogBrdList> findByMember_Id(String id) {
        return blogBrdListRepository.findByMember_Id(id);
    }
}
