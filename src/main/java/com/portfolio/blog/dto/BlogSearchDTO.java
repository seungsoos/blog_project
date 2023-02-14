package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogSearchDTO {

    private  String searchDateType;
    private  String searchBy;
    private Authority authority;

    private  String searchQuery = "";
}
