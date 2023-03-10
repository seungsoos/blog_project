package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.dto.BlogBrdListDTO;
import com.portfolio.blog.dto.BlogInfoDTO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_brd_list")
@Table(name="blog_brd_list")
@Data
@ToString
public class BlogBrdList extends  BaseTimeEntity{ // 블로그안에서 게시물 리스트
    @Id
    @Column(name="c_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long  cnum;

    // 유저 한명은 여러 게시글을 쓸수 있다
    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority brdRead; // 게시글 내용 읽기 권한

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  Authority brdWrite; // 게시글 댓글 쓰기 권한


    public void modifyBlogBrdList(BlogBrdListDTO blogBrdListDTO) {
        this.cnum = blogBrdListDTO.getCnum();
        this.brdRead = blogBrdListDTO.getBrdRead();
        this.brdWrite = blogBrdListDTO.getBrdWrite();
        this.member = blogBrdListDTO.getId();
    }
}
