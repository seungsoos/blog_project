package com.portfolio.blog.service;

import com.portfolio.blog.entity.BlogImgFile;
import org.springframework.web.multipart.MultipartFile;

public interface BlogImgFileService {

    //블로그 로고 등록
    public void saveBlogLogo(BlogImgFile blogImgFile,
                             MultipartFile blogLogoImg);
}
