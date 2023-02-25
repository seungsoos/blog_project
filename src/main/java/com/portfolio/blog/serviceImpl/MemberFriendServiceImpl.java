package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.MemberFriendDTO;
import com.portfolio.blog.entity.MemberFriend;
import com.portfolio.blog.repository.MemberFriendRepository;
import com.portfolio.blog.service.MemberFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFriendServiceImpl implements MemberFriendService {

    private final MemberFriendRepository memberFriendRepository;

    //친구추가
    @Override
    public void saveFriendList(MemberFriendDTO memberFriendDTO) {
        MemberFriend memberFriend = memberFriendDTO.createMemberFriend();
        memberFriendRepository.save(memberFriend);
    }

    //친구삭제
    @Override
    public void deleteFriendList(MemberFriendDTO memberFriendDTO) {
        MemberFriend memberFriend = memberFriendDTO.createMemberFriend();
        String loginId = memberFriend.getLoginId();
        String friendId = memberFriend.getFriendId();
        memberFriendRepository.deleteByLoginIdAndFriendId(loginId, friendId);
    }
}
