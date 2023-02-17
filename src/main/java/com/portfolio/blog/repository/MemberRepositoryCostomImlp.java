package com.portfolio.blog.repository;

import com.portfolio.blog.dto.MemberSearchDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryCostomImlp implements MemberRepositoryCostom{
        private JPAQueryFactory queryFactory;

    public  MemberRepositoryCostomImlp(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private  BooleanExpression searchByLike(String searchBy, String searchQuery){

        System.out.println(searchBy+"---------"+searchQuery);
        if(StringUtils.equals("nickName", searchBy)){
            return QMember.member.nickName.like("%"+searchQuery+"%");
        } else if (StringUtils.equals("id", searchBy)) {
            return  QMember.member.id.like("%"+searchQuery+"%");

        } else if (StringUtils.equals("name", searchBy)) {
            return QMember.member.name.like("%"+searchQuery+"%");
        }
        return  null;
    }
    @Override
    public Page<Member> getMemberList(MemberSearchDTO memberSearchDTO, Pageable pageable) {
        System.out.println(memberSearchDTO +"------------------"+pageable);
        List<Member> MemberList = queryFactory
                .selectFrom(QMember.member)
                .where(
                        searchByLike(memberSearchDTO.getSearchBy(), memberSearchDTO.getSearchQuery())
                )
                .orderBy(QMember.member.name.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(QMember.member)
                .where(
                        searchByLike(memberSearchDTO.getSearchBy(), memberSearchDTO.getSearchQuery())
                )
                .fetchOne();

        return new PageImpl<>(MemberList, pageable, total);
    }
}
