package com.portfolio.blog.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ImgFileDTO {
    private  Long imgNum;

    private String logoImg;

    private  String logoImgPath;

    private  String mainImg;

    private  String mainImgPath;

    private  String mainImgExt;

    private  String logImgExt;
}
