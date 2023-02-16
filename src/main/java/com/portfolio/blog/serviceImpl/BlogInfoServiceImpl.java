package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.repository.BlogImgFileRepository;
import com.portfolio.blog.repository.BlogInfoRepository;
import com.portfolio.blog.service.BlogImgFileService;
import com.portfolio.blog.service.BlogInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogInfoServiceImpl implements BlogInfoService {

    private final BlogInfoRepository blogInfoRepository;
    private final BlogImgFileService blogImgFileService;

    @Override
    public BlogInfo findByMember(String id) {
        return blogInfoRepository.findByMember(id);
    }
    
    //블로그 정보 저장
    @Override
    public void saveBlogInfo(BlogInfoDTO blogInfoDTO,
                             List<MultipartFile> blogLogoImg) {
        BlogInfo blogInfo = blogInfoDTO.saveBlogInfo();
        blogInfoRepository.save(blogInfo);

        try {
            if (!(blogLogoImg.isEmpty())){

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
