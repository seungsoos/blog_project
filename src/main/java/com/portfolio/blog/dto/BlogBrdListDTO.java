package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
public class BlogBrdListDTO {
    private  Long  cnum;


    private  String id;

    private  String brdTitle;

    @Enumerated(EnumType.STRING)
    private Authority brdRead; // 읽기 권한

    @Enumerated(EnumType.STRING)
    private  Authority brdWrite; // 댓글 권한

}
