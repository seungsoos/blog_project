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
    //친구추가 중복검사
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
    public List<MemberFriend> findByLoginId(String loginId) {
        return memberFriendRepository.findByLoginId(loginId);
    }

    @Override
    public List<MemberFriend> findByFriendId(String friendId) {
        return memberFriendRepository.findByFriendId(friendId);
    }
}
