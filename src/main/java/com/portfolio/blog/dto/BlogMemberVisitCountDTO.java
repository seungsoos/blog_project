package com.portfolio.blog.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;

@Data
@ToString
public class BlogMemberVisitCountDTO {
    private  Long mnum;

    private  String id;

}
