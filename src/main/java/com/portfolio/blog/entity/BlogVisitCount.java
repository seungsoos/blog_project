package com.portfolio.blog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="blog_visit_count")
@Data
@ToString
public class BlogVisitCount {
    @Id
    @Column(name="v_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long vnum;

    private String visitDate;
}
