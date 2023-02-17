package com.portfolio.blog.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberSearchDTO {
    private  String searchBy; // 유저 조건

    private  String searchQuery = ""; // 검색어
}
