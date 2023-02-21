package com.portfolio.blog.repository;

import com.portfolio.blog.dto.MemberSearchDTO;
import com.portfolio.blog.entity.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface MemberRepositoryCustom {
    Page<Member> getMemberList(MemberSearchDTO memberSearchDTO, Pageable pageable);
}
