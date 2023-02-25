package com.portfolio.blog.repository;

import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.entity.MemberFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberFriendRepository extends JpaRepository<MemberFriend, Long> {

    void deleteByLoginIdAndFriendId(String loginId, String friendId);

    int countByLoginIdAndFriendId(String loginId, String friendId);

    List<MemberFriend> findByFriendIdAndType(String friendId, FriendShip friendShip);
    List<MemberFriend> findByLoginId(String loginId);
    List<MemberFriend> findByFriendId(String friendId);

    MemberFriend findByLoginIdAndFriendId(String loginId, String friendId);

}