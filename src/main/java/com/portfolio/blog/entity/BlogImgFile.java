package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
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

    private String logoImg;

    private  String logoImgPath;

    private  String mainImg;

    private  String mainImgPath;

    private  String mainImgExt;

    private  String logImgExt;
}
