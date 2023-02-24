package com.portfolio.blog.service;
import com.portfolio.blog.dto.MemberFriendDTO;

public interface MemberFriendService {
        void saveFriendList(MemberFriendDTO memberFriendDTO);

    int countByLoginIdAndFriendId(String loginId, String friendId);
    }
