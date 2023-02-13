package com.portfolio.blog.entity;

import com.portfolio.blog.dto.BlogVisitCountDTO;
import com.portfolio.blog.repository.BlogVisitCountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class BlogVisitCountTest {
    @Autowired
    BlogVisitCountRepository blogVisitCountRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public  void BlogVisitCount(){
        BlogVisitCount blogVisitCount = new BlogVisitCount();
        blogVisitCountRepository.save(blogVisitCount);
    }
}