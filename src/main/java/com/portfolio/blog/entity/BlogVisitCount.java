package com.portfolio.blog.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

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
    @CreatedDate
    @Column(updatable = false) // 엔티티가 생성되어 저장될 때 시간을 자동으로 저장
    private String visitDate;
}
