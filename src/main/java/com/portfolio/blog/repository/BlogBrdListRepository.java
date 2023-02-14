package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogBrdList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;

import java.util.List;

public interface BlogBrdListRepository extends JpaRepository<BlogBrdList, Long> {


    // 개인 블로그 안에 게시글 리스트 뽑기
    List<BlogBrdList> findByMember(String id);

    List<BlogBrdList> findAll();

}
