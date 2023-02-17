package com.portfolio.blog.repository;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.QBlogList;
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
import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class BlogListRepositoryCostomImpl  implements  BlogListRepositoryCostom {

    private JPAQueryFactory queryFactory;

    public  BlogListRepositoryCostomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchAuthorityEq(Authority searchAuthority){
        return searchAuthority == null ? null : QBlogList.blogList.blogAuthority.eq(searchAuthority);
    }

    private  BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType ==null){
            return null;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        }else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        }else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }
        return  QBlogList.blogList.regTime.after(dateTime);
    }

    private  BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("blogName", searchBy)){
            return QBlogList.blogList.blogName.like("%"+searchQuery+"%");
        } else if (StringUtils.equals("blogDetail", searchBy)) {
            return  QBlogList.blogList.blogDetail.like("%"+searchQuery+"%");

        } else if (StringUtils.equals("member", searchBy)) {
            return QBlogList.blogList.member.nickName.like("%"+searchQuery+"%");
        }
        return  null;
    }
    @Override
    public Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable) {

        List<BlogList> content = queryFactory
                .selectFrom(QBlogList.blogList)
                .where(regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery())
                )
                .orderBy(QBlogList.blogList.regTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

         Long total = queryFactory
                .select(Wildcard.count)
                .from(QBlogList.blogList)
                .where(regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
