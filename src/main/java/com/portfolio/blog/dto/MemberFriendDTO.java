package com.portfolio.blog.dto;


import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.entity.MemberFriend;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;


@Data
@ToString
public class MemberFriendDTO {

    private Long bnum;

    private String loginId;

    private String friendId;

    private FriendShip type;

    private static ModelMapper modelMapper = new ModelMapper();

    //DTO를 -> Entity로 변경
    public MemberFriend createMemberFriend(){
        return modelMapper.map(this, MemberFriend.class);
    }
    //Entity를 -> DTO로 변경
    public static MemberFriendDTO of(MemberFriend memberFriend){
        return modelMapper.map(memberFriend, MemberFriendDTO.class);
    }


}
