package com.portfolio.blog.repository;

import com.portfolio.blog.dto.MemberSearchDTO;
import com.portfolio.blog.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCostom {
    Page<Member> getMemberList(MemberSearchDTO memberSearchDTO, Pageable pageable);
}
