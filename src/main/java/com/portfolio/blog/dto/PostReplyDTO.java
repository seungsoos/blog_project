package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.PostReply;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Data
@ToString
public class PostReplyDTO {
    private Long rnum;
    private Member id;

    private BlogPost blogPost;
    private  String replyTitle;
    private  String replyText;
    @Enumerated(EnumType.STRING)
    private Authority replyAuthority;

    private static ModelMapper modelMapper = new ModelMapper();
    //DTO -> Entity로 변경
    public PostReply createPostReply(){
        return modelMapper.map(this, PostReply.class);
    }
    //DTO -> Entity로 변경
    public static PostReplyDTO of(PostReply postReply){
        return modelMapper.map(postReply, PostReplyDTO.class);
    }
}
