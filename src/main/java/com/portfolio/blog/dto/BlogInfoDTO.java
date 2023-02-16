package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BlogInfoDTO {
    private Long inum;

    private Member member;

    @Enumerated(EnumType.STRING)
    private Authority blogLogo; // 블로그 로고

    private String  myProfile; // 개인 소개

    private  String titleBgColor; // 타이틀 컬러

    private String sideBgColor; // 사이드 컬러

    private String boxBgColor; //본문 컬러

    @Enumerated(EnumType.STRING)
    private Authority viewChk; // 프로필 공개유무

    private static ModelMapper modelMapper = new ModelMapper();
    //DTO -> Entity로 변경
    public BlogInfo saveBlogInfo(){
        return modelMapper.map(this, BlogInfo.class);
    }

    //DTO -> Entity로 변경
    public static BlogInfoDTO of(BlogInfo blogInfo){
        return modelMapper.map(blogInfo, BlogInfoDTO.class);
    }
}
