package com.portfolio.blog.dto;

import com.portfolio.blog.entity.MemberFriend;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@ToString
@Data
public class MemberFriendDTO {
    private  String FriendId;
    private  String LoginId;
    private static ModelMapper modelMapper = new ModelMapper();


    public MemberFriend createMemberFriend(){
        return modelMapper.map(this, MemberFriend.class);
    }
    //Entity를 -> DTO로 변경
    public static MemberFriendDTO of(MemberFriend memberFriend){
        return modelMapper.map(memberFriend, MemberFriendDTO.class);
    }

}
