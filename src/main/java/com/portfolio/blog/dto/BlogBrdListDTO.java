package com.portfolio.blog.dto;

import com.portfolio.blog.constant.Authority;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BlogBrdListDTO {
    private  Long  cnum;


    private  String id;

    private  String brdTitle;

    @Enumerated(EnumType.STRING)
    private Authority brdRead;

    @Enumerated(EnumType.STRING)
    private  Authority brdWrite;

    private  String brdWDate;

}
