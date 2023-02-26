package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
public class BlogBrdListDTO {
    private  Long  cnum;

    private Member id;

    @Enumerated(EnumType.STRING)
    private Authority brdRead; // 읽기 권한

    @Enumerated(EnumType.STRING)
    private  Authority brdWrite; // 댓글 권한

    private static ModelMapper modelMapper = new ModelMapper();

    //DTO -> Entity로 변경
    public BlogBrdList createBlogBrdList(){
        return modelMapper.map(this, BlogBrdList.class);
    }

    //DTO -> Entity로 변경
    public static BlogBrdListDTO of(BlogBrdList blogBrdList){
        return modelMapper.map(blogBrdList, BlogBrdListDTO.class);
    }
}
