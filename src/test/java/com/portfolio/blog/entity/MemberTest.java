package com.portfolio.blog.entity;

import com.portfolio.blog.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    // 영속성 컨텍스트 적용
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("유저 회원가입")
    public void createMember() {
       System.out.println("테스트 코드 ");

        Member member = new Member();

        member.setId("ABCD");
        member.setNickName("니찌릿나");
        member.setName("TH");
        memberRepository.save(member);

        em.flush();
        em.clear();

        Member member1 = memberRepository.save(memberRepository.findById(member.getId())
                .orElseThrow(EntityNotFoundException::new));

        System.out.println("-----------------");
        System.out.println("nick name : "+member1.getNickName());
        System.out.println("name : "+member1.getName());
        System.out.println("member id : "+member1.getId());
    }

    @Test
    @DisplayName("유저 정보수정")
    public void modifyMember(){
        System.out.println("테스트 코드 ");

        Member member = new Member();

        member.setId("ABCD");
        member.setNickName("니찌릿나");
        member.setName("TH");
        memberRepository.save(member);

        em.flush();
        Member member1 = memberRepository.save(memberRepository.findById(member.getId())
                .orElseThrow(EntityNotFoundException::new));
        System.out.println("-----------------");
        System.out.println("nick name : "+member1.getNickName());
        System.out.println("name : "+member1.getName());
        System.out.println("member id : "+member1.getId());

        member.setName("SU");
        member.setNickName("워찌릿다");
        em.flush();
        em.clear();

        Member member2 = memberRepository.save(memberRepository.findById(member.getId())
                .orElseThrow(EntityNotFoundException::new));
        System.out.println("-----------------");
        System.out.println("nick name : "+member2.getNickName());
        System.out.println("name : "+member2.getName());
        System.out.println("member id : "+member2.getId());

    }
}