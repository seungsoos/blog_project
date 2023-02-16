package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.entity.BlogImgFile;
import com.portfolio.blog.service.BlogImgFileService;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

public class BlogImgFileServiceImpl implements BlogImgFileService {
    
    //블로고 로고 등록
    @Override
    public void saveBlogLogo(BlogImgFile blogImgFile,
                             MultipartFile blogLogoImg) {
        String oriImgName = blogLogoImg.getOriginalFilename();
        String logoImg = "";
        String logoImgPath ="";
        String logoImgExt = "";

        if(!StringUtils.isEmpty(oriImgName)){
//            logoImg =
        }

    }
}
