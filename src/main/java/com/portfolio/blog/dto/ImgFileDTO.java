package com.portfolio.blog.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ImgFileDTO {
    private  Long imgNum;

    private String logoImg;

    private  String logoImgPath; // 로고 이미지

    private  String mainImg; //메인 이미지

    private  String mainImgPath; // 메인이미지 주소

    private  String mainImgExt; // 메인이미지 확장자

    private  String logImgExt; // 로고 이미지 확장자
}
