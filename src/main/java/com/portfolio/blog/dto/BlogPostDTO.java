package com.portfolio.blog.dto;

import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class BlogPostDTO {
    private  Long pnum;

    private  String postTitle;

    private  String postText;


    //게시글 작성 유저
    private Member member;

    // 게시글 분류
    private BlogBrdList blogBrdList;
}
