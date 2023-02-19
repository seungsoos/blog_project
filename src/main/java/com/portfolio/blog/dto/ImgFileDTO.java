package com.portfolio.blog.dto;

import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ImgFileDTO {
    private  Long imgNum;

    private String member;

    private String uuid;

    private  String postFileName; // 게시글 제목

    private  String postFilePath; // 게시글 주소

    private  String postFileExt; // 메인이미지 확장자

}
