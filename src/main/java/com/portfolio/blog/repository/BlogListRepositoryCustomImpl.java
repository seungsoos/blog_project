package com.portfolio.blog.repository;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.QBlogList;
import com.portfolio.blog.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Primary
public class BlogListRepositoryCustomImpl implements BlogListRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public BlogListRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchAuthorityEq(Authority searchAuthority){
        return searchAuthority == null ? null : QBlogList.blogList.blogAuthority.eq(searchAuthority);
    }

    private BooleanExpression memberBlog(Member id){
        return  id == null ? null : QBlogList.blogList.member.eq(id);
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
        }
        return  null;
    }
    @Override
    public Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable) {

        List<BlogList> content = queryFactory
                .selectFrom(QBlogList.blogList)
                .where(regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getBlogAuthority()),
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
                        searchAuthorityEq(blogSearchDTO.getBlogAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
