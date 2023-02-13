package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.service.BlogBrdListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogBrdListServiceImpl implements BlogBrdListService {

    private  final  BlogBrdListService blogBrdListService;
    @Override
    public List<BlogBrdList> findByMember(String id) {
        return blogBrdListService.findByMember(id);
    }
}
