package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.dto.MemberFriendDTO;
import com.portfolio.blog.entity.MemberFriend;
import com.portfolio.blog.repository.MemberFriendRepository;
import com.portfolio.blog.service.MemberFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFriendServiceImpl implements MemberFriendService {

    private final MemberFriendRepository memberFriendRepository;

    @Override
    public void saveFriendList(MemberFriendDTO memberFriendDTO) {
        MemberFriend memberFriend = memberFriendDTO.createMemberFriend();
        memberFriendRepository.save(memberFriend);
    }

    @Override
    public int countByLoginIdAndFriendId(String loginId, String friendId) {
        return memberFriendRepository.countByLoginIdAndFriendId(loginId, friendId);
    }

    @Override
    public List<MemberFriend> findByFriendIdAndType(String friendId, FriendShip friendShip){
        return memberFriendRepository.findByFriendIdAndType(friendId, friendShip);
    }

    @Override
    public MemberFriend findByLoginIdAndFriendId(String loginId, String friendId) {
        return memberFriendRepository.findByLoginIdAndFriendId(loginId, friendId);
    }
    @Override
    public void updateMemberFriend(String loginId, String friendId){
        MemberFriend  memberFriend = memberFriendRepository.findByLoginIdAndFriendId(friendId, loginId);
        memberFriend.setType(FriendShip.FRIENDS);
    }

    @Override
    public void deleteByLoginIdAndFriendId(String loginId, String friendId) {
        memberFriendRepository.deleteByLoginIdAndFriendId(friendId, loginId);
    }

    @Override
    public List<MemberFriend> findByLoginId(String loginId) {
        return memberFriendRepository.findByLoginId(loginId);
    }

    @Override
    public List<MemberFriend> findByFriendId(String friendId) {
        return memberFriendRepository.findByFriendId(friendId);
    }
}