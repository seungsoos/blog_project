package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_post_img")
@Table(name="blog_post_img")
@Data
@ToString
public class BlogPostImg extends BaseTimeEntity{ // 블로그 양식 설정시 들어갈 이미지 파일 테이블
    @Id
    @Column(name="img_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long imgNum;

    @JoinColumn(name="p_num")
    @ManyToOne
    private  BlogPost blogPost;

    @Column(nullable = false)
    private String imgName; //이미지이름

    @Column(nullable = false)
    private  String imgPath; // 주소

    public void updateBlogPostImg(String imgPath, String imgName){
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
