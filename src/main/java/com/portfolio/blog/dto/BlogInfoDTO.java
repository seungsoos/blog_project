package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
public class BlogInfoDTO {
    private Long inum;

    private  String id;

    private char blogLogo;

    private String  my_profile;

    private  String titleColor;
    private  String sideColor;

    private String boxBgColor;

    @Enumerated(EnumType.STRING)
    private Authority viewChk; // 개인 프로필 공개여부

}
