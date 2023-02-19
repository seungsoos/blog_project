package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Category;
import com.portfolio.blog.dto.ImgFileDTO;
import com.portfolio.blog.repository.BlogBrdListRepository;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name="blog_post")
@Table(name="blog_post")
@Data
@ToString
public class BlogPost extends  BaseTimeEntity{
    @Id
    @Column(name="p_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pnum;

    @JoinColumn(name="c_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private BlogBrdList blogBrdList;    //BlogBrdList FK

    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;    //게시글 작성 유저

    @Column(nullable = false)
    private String postTitle; // 게시글 제목
    @Column(nullable = false)
    private String postText; // 게시글 본문

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;  // 게시글 분류

}
