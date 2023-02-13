package com.portfolio.blog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="blog_list")
@Data
@ToString
public class BlogList {
    @Id
    @Column(name="b_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long bnum;

    // 한명의 유저는 하나의 블로그를 개설할 수 있다.
    @JoinColumn(name="Member_id")
    @OneToOne
    private Member member;

    private String blogName;

    private  String blogDetail;


}
