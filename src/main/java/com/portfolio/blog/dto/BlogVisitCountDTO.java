package com.portfolio.blog.dto;

import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogVisitCount;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Data
@ToString
public class BlogVisitCountDTO {
    private  Long vnum;

    private static ModelMapper modelMapper = new ModelMapper();

    private BlogList blogList;

    //DTO -> Entity로 변경
    public BlogVisitCount createBlogMemberVisitCount(){
        return modelMapper.map(this, BlogVisitCount.class);
    }

    //DTO -> Entity로 변경
    public static BlogVisitCountDTO of(BlogVisitCount blogVisitCount){
        return modelMapper.map(blogVisitCount, BlogVisitCountDTO.class);
    }
}
