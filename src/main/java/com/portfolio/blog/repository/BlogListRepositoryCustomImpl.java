package com.portfolio.blog.repository;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.*;
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

    private BooleanExpression searchBnum(Long bnum){
        if(bnum != null){
            return QBlogList.blogList.bnum.ne(bnum);
        }
        return null;
    }

    @Override
    public Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable) {

        List<BlogList> content = queryFactory
                .selectFrom(QBlogList.blogList)
                .where(regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getBlogAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery()),
                        searchBnum(blogSearchDTO.getBnum()),
                        QBlogList.blogList.blogAuthority.eq(Authority.PERMISSION)
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
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery()),
                        searchBnum(blogSearchDTO.getBnum()),
                        QBlogList.blogList.blogAuthority.eq(Authority.PERMISSION)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    //친구목록
    @Override
    public Page<MemberFriend> getFriendBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable, String loginId) {
        List<MemberFriend> content = queryFactory
                .selectFrom(QMemberFriend.memberFriend).distinct()
                .join(QBlogList.blogList)
                .on(QMemberFriend.memberFriend.loginId.eq(QBlogList.blogList.member.id)
                        .or(QMemberFriend.memberFriend.friendId.eq(QBlogList.blogList.member.id)))
                .where(QMemberFriend.memberFriend.loginId.eq(loginId)
                                .or(QMemberFriend.memberFriend.friendId.eq(loginId)),
                        QMemberFriend.memberFriend.type.eq(FriendShip.FRIENDS),
                        QBlogList.blogList.blogAuthority.eq(Authority.PERMISSION)
                                .or(QBlogList.blogList.blogAuthority.eq(Authority.GROUP)),
                        regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getBlogAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery()),
                        QMemberFriend.memberFriend.fnum.ne(blogSearchDTO.getBnum())
                )
                .orderBy(QMemberFriend.memberFriend.regTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        /*List<MemberFriend> content = queryFactory
                .selectFrom(QMemberFriend.memberFriend)
                .where(QMemberFriend.memberFriend.loginId.eq(loginId)
                                .or(QMemberFriend.memberFriend.friendId.eq(loginId)),
                        QMemberFriend.memberFriend.type.eq(FriendShip.FRIENDS),
                        regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getBlogAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery()),
                        QMemberFriend.memberFriend.fnum.ne(blogSearchDTO.getBnum())
                )
                .orderBy(QMemberFriend.memberFriend.regTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();*/

        Long total = queryFactory
                .select(Wildcard.count)
                .from(QMemberFriend.memberFriend)
                .where(regDtsAfter(blogSearchDTO.getSearchDateType()),
                        searchAuthorityEq(blogSearchDTO.getBlogAuthority()),
                        searchByLike(blogSearchDTO.getSearchBy(), blogSearchDTO.getSearchQuery()),
                        QMemberFriend.memberFriend.fnum.eq(blogSearchDTO.getBnum())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
