package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.FriendShip;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="friend_ship")
@Table(name="friend_ship")
@Data
@ToString
public class MemberFriend extends BaseTimeEntity implements Serializable {

    @Id
    @JoinColumn(name="Member_id")
    private String user_first_id;

    @Id
    @JoinColumn(name="Member_id")
    private String user_second_id;


    @Enumerated(EnumType.STRING)
    private FriendShip type;
}
