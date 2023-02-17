package com.portfolio.blog.entity;

import com.portfolio.blog.repository.BlogBrdListRepository;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_post")
@Table(name="blog_post")
@Data
@ToString
public class BlogPost extends  BaseTimeEntity{
    @Id
    @Column(name="p_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long pnum;

    private  String postTitle;

    private  String postText;


    //게시글 작성 유저
    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member member;

    // 게시글 분류
    @JoinColumn(name="b_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private BlogBrdList blogBrdList;

}
