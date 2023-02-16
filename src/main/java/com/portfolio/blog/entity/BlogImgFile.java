package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_img_file")
@Table(name="blog_img_file")
@Data
@ToString
public class BlogImgFile extends BaseTimeEntity{ // 블로그 양식 설정시 들어갈 이미지 파일 테이블 
    @Id
    @Column(name="img_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long imgNum;

    @JoinColumn(name="Member_id")
    @OneToOne
    private  Member member;

    @Column(nullable = false)
    private String logoImg; //이미지이름

    @Column(nullable = false)
    private  String logoImgPath; // 주소

    @Column(nullable = false)
    private  String logImgExt; //확장자
    
    @Column(nullable = false)
    private  String mainImg;

    @Column(nullable = false)
    private  String mainImgPath;

    @Column(nullable = false)
    private  String mainImgExt;

}
