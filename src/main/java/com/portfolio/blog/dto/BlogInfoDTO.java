package com.portfolio.blog.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BlogInfoDTO {
    private Long inum;

    private  String id;

    private char blogLogo; // 블로그 로고

    private String  my_profile; // 한줄 블로그소개

    private  String titleBgColor; // 타이틀 컬러

    private String sideBgColor; // 사이드 컬러

    private String boxBgColor; //본문 컬러

    @Enumerated(EnumType.STRING)
    private char viewChk; // 프로필 공개유무

}
