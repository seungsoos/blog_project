package com.portfolio.blog.repository;

import com.portfolio.blog.entity.MemberFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFriendRepository extends JpaRepository<MemberFriend, Long> {

    void deleteByLoginIdAndFriendId(String loginId, String friendId);

    int countByLoginIdAndFriendId(String loginId, String friendId);
}