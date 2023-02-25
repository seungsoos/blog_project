package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_visit_count")
@Table(name="blog_visit_count")
@Data
@ToString
public class BlogVisitCount extends BaseTimeEntity{
    @Id
    @Column(name="v_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long vnum;

    @JoinColumn(name="b_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private  BlogList blogList;
}
