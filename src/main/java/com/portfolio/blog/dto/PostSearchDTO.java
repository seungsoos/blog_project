package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.Category;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostSearchDTO {
    private  String searchDateType; // 블로그 최근게시글
    private  String searchBy; // 블로그 조건
    private Authority brdRead; // 읽기범위
    private Category category; // 읽기범위
    private Authority brdWrite; // 쓰기범위
    private  String searchQuery = "";
    private  Long bnum;
}
