package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.Role;
import com.portfolio.blog.dto.BlogInfoDTO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="blog_info")
@Table(name="blog_info")
@Data
@ToString
public class BlogInfo extends BaseTimeEntity {
    @Id
    @Column(name="i_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inum;

    // 한명의 유저는 하나의 블로그 양식을 가질 수 있다.
    @JoinColumn(name="Member_id")
    @OneToOne
    private  Member member;

    private String  myProfile;

    @Enumerated(EnumType.STRING)
    private  Authority viewChk; // 개인 프로필 공개 여부

    //정보수정
    public void modifyBlogInfo(BlogInfoDTO blogInfoDTO){
        this.inum = blogInfoDTO.getInum();
        this.member = blogInfoDTO.getId();
        this.myProfile = blogInfoDTO.getMyProfile();
        this.viewChk = blogInfoDTO.getViewChk();

    }

}
