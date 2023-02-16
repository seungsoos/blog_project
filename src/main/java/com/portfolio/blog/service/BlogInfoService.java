package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.entity.BlogInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogInfoService{
    BlogInfo findByMember(String id);

    //블로그 정보저장
    public void saveBlogInfo(BlogInfoDTO blogInfoDTO,
                             List<MultipartFile> blogLogoImg);
}
