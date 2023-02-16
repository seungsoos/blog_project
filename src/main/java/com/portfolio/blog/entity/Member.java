package com.portfolio.blog.entity;

import com.portfolio.blog.dto.MemberDTO;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.ToString;

import javax.persistence.*;

    @Entity(name="member")
    @Table(name="member")
    @Data
    @ToString
    public class Member extends BaseTimeEntity{

        @Id
        @Column(name="member_id")
        private String id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false, unique = true)
        private String nickName;
        @Column(nullable = false)
        private String password;

        public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
            Member member = new Member();
            // dto -> entity : 1:1 맵핑
            member.setName(memberDTO.getName());
            member.setId(memberDTO.getId());
            member.setNickName(memberDTO.getNickName());


            // 비밀번호를 암호화 처리
            String password = passwordEncoder.encode(memberDTO.getPassword());
            member.setPassword(password);

            return member;
        }


    }

