package com.portfolio.blog.dto;

import lombok.Data;

@Data
public class BlogBrdListDTO {
    private  Long  cnum;


    private  String id;

    private  String brdTitle;

    private  char brdRead;

    private  char brdWrite;

    private  String brdWDate;

}
