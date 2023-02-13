package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="blog_brd_list")
@Data
@ToString
public class BlogBrdList { // 블로그안에서 게시물 리스트
    @Id
    @Column(name="c_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long  cnum;

    // 유저 한명은 여러 게시글을 쓸수 있다
    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member member;

    private  String brdTitle; // 게시글 분류 이름

    private  char brdRead; // 게시글 내용 읽기 권한

    private  char brdWrite; // 게시글 댓글 쓰기 권한

    private  String brdWDate; // 게시글 생성 날짜
}
