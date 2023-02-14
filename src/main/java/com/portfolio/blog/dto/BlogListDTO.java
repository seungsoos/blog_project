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

    private  String nickName;

    private String blogName;

    private  String blogDetail;


    @Enumerated(EnumType.STRING)
    private Authority blogAuthority;

}
