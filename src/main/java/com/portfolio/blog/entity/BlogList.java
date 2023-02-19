package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.dto.BlogListDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity(name="blog_list")
@Table(name="blog_list")
@Data
@ToString
public class BlogList extends  BaseTimeEntity{
    @Id
    @Column(name="b_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long bnum;

    // 한명의 유저는 하나의 블로그를 개설할 수 있다.
    @JoinColumn(name="Member_id")
    @OneToOne
    private Member member;
    @Column(nullable = false)
    private String blogName;

    private  String blogDetail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority blogAuthority ; // 블로그 공개범위

    //정보수정
    public void modifyBlogList(BlogListDTO blogListDTO){
        this.bnum = blogListDTO.getBnum();
        this.member = blogListDTO.getId();
        this.blogName = blogListDTO.getBlogName();
        this.blogDetail = blogListDTO.getBlogDetail();
        this.blogAuthority = blogListDTO.getBlogAuthority();
    }
}
