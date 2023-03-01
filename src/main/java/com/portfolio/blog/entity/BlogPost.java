package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Category;
import com.portfolio.blog.dto.BlogPostDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="blog_post")
@Table(name="blog_post")
@Getter
@Setter
@ToString
public class BlogPost extends  BaseTimeEntity{
    @Id
    @Column(name="p_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pnum;

    @JoinColumn(name="c_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private BlogBrdList blogBrdList;    //BlogBrdList FK
    @JoinColumn(name="b_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private BlogList blogList;    //BlogBrdList FK

    @JoinColumn(name="member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;    //게시글 작성 유저

    @Column(nullable = false)
    private String postTitle; // 게시글 제목

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String postText; // 게시글 본문

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;  // 게시글 분류

    public void modifyBlogPost(BlogPostDTO blogPostDTO) {
        this.pnum = blogPostDTO.getPnum();
        this.blogList = blogPostDTO.getBlogList();
        this.member = blogPostDTO.getId();
        this.postText = blogPostDTO.getPostText();
        this.blogBrdList = blogPostDTO.getBlogBrdList();
        this.category = blogPostDTO.getCategory();
        this.postTitle = blogPostDTO.getPostTitle();
    }
}
