package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.Category;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;


@Data
@ToString
public class BlogPostDTO {

    private  Long pnum;

    private BlogBrdList blogBrdList;

    private  String postTitle; //게시글 제목

    private  String postText; //게시글 본문

    private String member;  //게시글 작성 유저

    private Category category; // 게시글 분류
}
