package com.portfolio.blog.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.blog.entity.BlogInfo;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;


@Data
@ToString
public class MemberDTO {

    private String id;

    private String nickName;

    private  String name;

    private String password;

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO를 -> Entity로 변경
    public Member save(){
        return modelMapper.map(this, Member.class);
    }

    //Entity를 -> DTO로 변경
    public static MemberDTO of(Member member){
        return modelMapper.map(member, MemberDTO.class);
    }

}
