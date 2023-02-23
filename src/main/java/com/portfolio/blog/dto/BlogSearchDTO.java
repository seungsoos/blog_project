package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogSearchDTO {

    private  String searchDateType; // 최신 블로그
    private  String searchBy; // 블로그 조건
    private Authority blogAuthority; // 공개범위
    private  String searchQuery = "";
    private  Long bnum;
}
