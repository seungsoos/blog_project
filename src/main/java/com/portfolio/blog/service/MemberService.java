package com.portfolio.blog.service;

import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private  final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Optional<Member> findMember = memberRepository.findById(member.getId());
        if (findMember.isPresent()){
            throw new IllegalStateException ("이미 가입된 회원입니다.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()){
            return User.builder()
                .username(member.get().getId())
                .password(member.get().getPassword())
                .roles()
                .build();
        }
        else {
            throw new UsernameNotFoundException(id);
        }

    }
}
