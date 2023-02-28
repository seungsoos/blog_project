package com.portfolio.blog.entity;

import com.portfolio.blog.constant.FriendShip;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity(name="friend_ship")
@Table(name="friend_ship")
@Data
@DynamicInsert
public class MemberFriend extends BaseTimeEntity{

    @Id
    @Column(name="f_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fnum;

    @JoinColumn(name="Member_id")
    private String loginId;

    @JoinColumn(name="Member_id")
    private String friendId;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'STANDBY'")
    private FriendShip type = FriendShip.STANDBY;

}
