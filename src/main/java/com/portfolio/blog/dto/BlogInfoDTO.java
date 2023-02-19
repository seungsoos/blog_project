package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogInfoDTO {
    private Long inum;

    private Member id;

    private String  myProfile; // 자기소개

    @Enumerated(EnumType.STRING)
    private Authority viewChk; // 프로필 공개유무


    /*@QueryProjection
    public BlogInfoDTO(Member member){
        this.id = member.getId();
    }*/


    private static ModelMapper modelMapper = new ModelMapper();
    //DTO를 -> Entity로 변경
    public BlogInfo saveBlogInfo(){
        return modelMapper.map(this, BlogInfo.class);
    }

    //Entity를 -> DTO로 변경
    public static BlogInfoDTO of(BlogInfo blogInfo){
        return modelMapper.map(blogInfo, BlogInfoDTO.class);
    }
}
