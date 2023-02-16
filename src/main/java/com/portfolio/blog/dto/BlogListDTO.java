package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
public class BlogListDTO { // 유저 한명당 하나의 블로그를 가짐
    private  Long bnum;

    private Member member;

    private String blogName; // 블로그 이름

    private  String blogDetail; //블로그 설명
    
    @Enumerated(EnumType.STRING)
    private Authority blogAuthority; // 블로그 공개범위


    private static ModelMapper modelMapper = new ModelMapper();
    //DTO -> Entity로 변경
    public BlogList saveBlogList(){
        return modelMapper.map(this, BlogList.class);
    }

    //DTO -> Entity로 변경
    public static BlogListDTO of(BlogList blogList){
        return modelMapper.map(blogList, BlogListDTO.class);
    }
}
