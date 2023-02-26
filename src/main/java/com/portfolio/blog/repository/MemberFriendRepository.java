package com.portfolio.blog.repository;

import com.portfolio.blog.entity.MemberFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFriendRepository extends JpaRepository<MemberFriend, Long> {

    void deleteByLoginIdAndFriendId(String loginId, String friendId);
    //친구추가 중복검사
    int countByLoginIdAndFriendId(String loginId, String friendId);
}