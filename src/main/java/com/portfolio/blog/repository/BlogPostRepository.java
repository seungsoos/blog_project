package com.portfolio.blog.repository;

import com.portfolio.blog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>, BlogPostRepositoryCustom {

    List<BlogPost> findByMember_Id(String id);

    BlogPost findByBlogList_Bnum (Long bnum);

    BlogPost findByPnum(Long pnum);

}
