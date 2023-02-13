package com.portfolio.blog.dto;

import lombok.Data;

@Data
public class BlogListDTO { // 유저 한명장 하나의 블로그를 가짐
    private  Long bnum;

    private String userId;

    private  String nickName;

    private String blogName;

    private  String blogDetail;

}
