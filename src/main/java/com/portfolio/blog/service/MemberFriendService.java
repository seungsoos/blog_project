package com.portfolio.blog.service;
import com.portfolio.blog.dto.MemberFriendDTO;

public interface MemberFriendService {
    
    //친구추가
    void saveFriendList(MemberFriendDTO memberFriendDTO);
    //친구삭제
    void deleteFriendList(MemberFriendDTO memberFriendDTO);
    //친구추가 중복검사
    int countByLoginIdAndFriendId(String loginId, String friendId);

}
