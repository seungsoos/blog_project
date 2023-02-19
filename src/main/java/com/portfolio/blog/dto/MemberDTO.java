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

}
