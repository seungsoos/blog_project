package com.portfolio.blog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="blog_member_visit_count")
@Data
@ToString
public class BlogMemberVisitCount {
    @Id
    @Column(name="m_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long Mnum;

    // 한명의 유저는 여러 블로그를 볼 수 있다.
    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member member;

    private String visitDate;

}
