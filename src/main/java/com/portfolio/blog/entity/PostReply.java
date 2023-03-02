package com.portfolio.blog.entity;


import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="post_reply")
@Table(name="post_reply")
@Data
@ToString
public class PostReply extends BaseTimeEntity{

    @Id
    @Column(name="r_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rnum;
    @JoinColumn(name="member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member id;

    @JoinColumn(name="p_num")
    @ManyToOne
    private  BlogPost blogPost;
    private  String replyTitle;
    private  String replyText;
    @Enumerated(EnumType.STRING)
    private Authority replyAuthority;
}
