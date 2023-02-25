package com.portfolio.blog.service;

import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.dto.MemberFriendDTO;
import com.portfolio.blog.entity.MemberFriend;

import java.util.List;

public interface MemberFriendService {

    //친구추가
    void saveFriendList(MemberFriendDTO memberFriendDTO);

    //친구삭제
    void deleteFriendList(MemberFriendDTO memberFriendDTO);
    void saveFriendList(MemberFriendDTO memberFriendDTO);

    int countByLoginIdAndFriendId(String loginId, String friendId);

    void deleteByLoginIdAndFriendId(String loginId, String friendId);

    List<MemberFriend> findByFriendIdAndType(String friendId, FriendShip friendShip);

    List<MemberFriend> findByLoginId(String loginId);

    List<MemberFriend> findByFriendId(String friendId);

    MemberFriend findByLoginIdAndFriendId(String loginId, String friendId);

    void updateMemberFriend(String loginId, String friendId);

}
