package com.portfolio.blog.dto;

import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.BlogPostImg;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Data
@ToString
public class BlogPostImgDTO {
    private  Long imgNum;

    private BlogPost pnum;

    private  String imgPath; // 게시글 주소

    private  String imgName; // 메인이미지 확장자

    private static ModelMapper modelMapper = new ModelMapper();
    //DTO -> Entity로 변경
    public BlogPostImg createBlogMemberVisitCount(){
        return modelMapper.map(this, BlogPostImg.class);
    }

    //DTO -> Entity로 변경
    public static BlogPostImgDTO of(BlogPostImg blogPostImg){
        return modelMapper.map(blogPostImg, BlogPostImgDTO.class);
    }
}
