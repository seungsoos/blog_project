package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BlogInfoDTO {
    private Long inum;

    private  String id;

    private char blogLogo;

    private String  my_profile;

    private  String BgColor;

    private String boxBgColor;

    private char mainImg;

    @Enumerated(EnumType.STRING)
    private Authority viewChk;

}
