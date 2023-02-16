package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_info")
@Table(name="blog_info")
@Data
@ToString
public class BlogInfo {
    @Id
    @Column(name="i_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inum;

    // 한명의 유저는 하나의 블로그 양식을 가질 수 있다.
    @JoinColumn(name="Member_id")
    @OneToOne
    private  Member member;

    @Enumerated(EnumType.STRING)
    private Authority blogLogo;

    private String  myProfile;

    private  String titleColor; // 제목 컬러

    private  String sideColor; // 사이드 컬러

    private String boxBgColor; // 본문 컬러

    @Enumerated(EnumType.STRING)
    private  Authority viewChk; // 개인 프로필 공개 여부

}
