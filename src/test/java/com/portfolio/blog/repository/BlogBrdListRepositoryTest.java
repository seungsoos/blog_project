package com.portfolio.blog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BlogBrdListRepositoryTest {
    @Autowired
    BlogBrdListRepository blogBrdListRepository;

    @Test
    public void testClass(){
        System.out.println(blogBrdListRepository.getClass());
    }

}