package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
public class BlogListDTO { // 유저 한명장 하나의 블로그를 가짐
    private  Long bnum;

    private String userId;

    private  String nickName; // 닉네임

    private String blogName; // 블로그 이름

    private  String blogDetail; //블로그 설명
    
    @Enumerated(EnumType.STRING)
    private Authority blogAuthority; // 블로그 공개범위

}
