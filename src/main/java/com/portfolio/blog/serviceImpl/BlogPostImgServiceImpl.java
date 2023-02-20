package com.portfolio.blog.serviceImpl;
import com.portfolio.blog.dto.BlogPostImgDTO;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.BlogPostImg;
import com.portfolio.blog.repository.BlogPostImgRepository;
import com.portfolio.blog.service.BlogPostImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogPostImgServiceImpl implements BlogPostImgService {

    private  final BlogPostImgRepository blogPostImpRepository;

    @Override
    public List<BlogPostImg> findByPNum(Long pnum) {
        return blogPostImpRepository.findByBlogPost_pnum(pnum);
    }

    @Override
    public void saveBlogPostImg(BlogPostImgDTO blogPostImgDTO) {
        BlogPostImg blogPostImg = blogPostImgDTO.createBlogMemberVisitCount();
        blogPostImpRepository.save(blogPostImg);
    }
}
