package com.portfolio.blog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

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
    @CreatedDate
    @Column(updatable = false) // 엔티티가 생성되어 저장될 때 시간을 자동으로 저장
    private String visitDate;

}
