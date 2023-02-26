package com.portfolio.blog.dto;

import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogMemberVisitCount;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;


@Data
@ToString
public class BlogMemberVisitCountDTO {
    private  Long mnum;

    private Member id;

    private BlogList blogList;

    private static ModelMapper modelMapper = new ModelMapper();
    //DTO -> Entity로 변경
    public BlogMemberVisitCount createBlogMemberVisitCount(){
        return modelMapper.map(this, BlogMemberVisitCount.class);
    }

    //DTO -> Entity로 변경
    public static BlogMemberVisitCountDTO of(BlogMemberVisitCount blogMemberVisitCount){
        return modelMapper.map(blogMemberVisitCount, BlogMemberVisitCountDTO.class);
    }
}
