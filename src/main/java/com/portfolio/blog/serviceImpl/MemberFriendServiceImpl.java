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

    @Override
    public void saveFriendList(MemberFriendDTO memberFriendDTO) {
        MemberFriend memberFriend = memberFriendDTO.createMemberFriend();
        memberFriendRepository.save(memberFriend);
    }
}