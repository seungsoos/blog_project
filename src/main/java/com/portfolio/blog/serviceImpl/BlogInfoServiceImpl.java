package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.BlogInfoRepository;
import com.portfolio.blog.service.BlogInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogInfoServiceImpl implements BlogInfoService {

    private final BlogInfoRepository blogInfoRepository;


    @Override
    public BlogInfo findByMember_id(String id) {
        return blogInfoRepository.findByMember_id(id);
    }

    //블로그 정보 저장
    @Override
    public void saveBlogInfo(BlogInfoDTO blogInfoDTO) {
        BlogInfo blogInfo = blogInfoDTO.createBlogInfo();
        blogInfoRepository.save(blogInfo);
    }
    
    //블로그 정보 수정
    @Override
    public void modifyBlogInfo(BlogInfoDTO blogInfoDTO) {
        Member member = blogInfoDTO.getId();
        String id = member.getId();
        BlogInfo blogInfo = blogInfoRepository.findByMember_id(id);
        blogInfo.modifyBlogInfo(blogInfoDTO);
    }


}
