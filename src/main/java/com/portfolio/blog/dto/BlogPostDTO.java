package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Category;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;


@Data
@ToString
public class BlogPostDTO {

    private Long pnum;

    private BlogList blogList;

    private BlogBrdList blogBrdList;

    private String postTitle; //게시글 제목

    private String postText; //게시글 본문

    private Member id;  //게시글 작성 유저

    private Category category; // 게시글 분류


    private static ModelMapper modelMapper = new ModelMapper();

    //DTO -> Entity로 변경
    public BlogPost createBlogPost() {
        return modelMapper.map(this, BlogPost.class);
    }

    //DTO -> Entity로 변경
    public static BlogPostDTO of(BlogPost blogPost) {
        return modelMapper.map(blogPost, BlogPostDTO.class);
    }
}
