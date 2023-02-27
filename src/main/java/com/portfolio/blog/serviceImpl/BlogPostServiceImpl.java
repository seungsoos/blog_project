package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.BlogPostRepository;
import com.portfolio.blog.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;
    @Override
    public List<BlogPost> findByMember_Id(String id) {
        return blogPostRepository.findByMember_Id(id);
    }

    @Override
    public BlogPost findByBlogList_Bnum(Long bnum) {
        return blogPostRepository.findByBlogList_Bnum(bnum);
    }
    @Override
    public BlogPost findByPnum(Long pnum) {
        return blogPostRepository.findByPnum(pnum);
    }

    @Override
    public void modifyBlogPost(BlogPostDTO blogPostDTO) {
        Long Pnum = blogPostDTO.getPnum();
        BlogPost blogPost = blogPostRepository.findByPnum(Pnum);
        blogPost.modifyBlogPost(blogPostDTO);
    }

    @Override
    public void saveBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostDTO.createBlogPost();
        blogPostRepository.save(blogPost);
    }

    @Transactional(readOnly = true)
    public Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable){
        return blogPostRepository.getMemberBlogPage(postSearchDTO, pageable);
    }
}
