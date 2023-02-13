package com.portfolio.blog.entity;

import com.portfolio.blog.dto.MemberDTO;
import com.portfolio.blog.repository.BlogListRepository;
import com.portfolio.blog.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class BlogListTest {
    @Autowired
    BlogListRepository blogListRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("AAA");
        memberDTO.setName("홍길동");
        memberDTO.setNickName("니찌맀다");
        memberDTO.setPassword("1234");
        return Member.createMember(memberDTO, passwordEncoder);
    }
    @Test
    @DisplayName("개안 블로그, 회원 엔티티 맵핑")
    public void findCartAndMemberTest() {
        Member member = createMember();
        memberRepository.save(member);

        BlogList blogList = new BlogList();
        blogList.setMember(member);
        blogListRepository.save(blogList);
    }
}