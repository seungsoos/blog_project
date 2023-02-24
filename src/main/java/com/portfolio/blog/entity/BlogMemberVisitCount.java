package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_member_visit_count")
@Table(name="blog_member_visit_count")
@Data
@ToString
public class BlogMemberVisitCount extends  BaseTimeEntity{
    @Id
    @Column(name="m_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long mnum;

    // 한명의 유저는 여러 블로그를 볼 수 있다.
    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member member;

    // 하나의 블로그에 여러명이 올수 있다.
    @JoinColumn(name="b_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private  BlogList blogList;
}
