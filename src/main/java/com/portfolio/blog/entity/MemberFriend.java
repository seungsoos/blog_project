package com.portfolio.blog.entity;

import com.portfolio.blog.constant.FriendShip;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name="friend_ship")
@Table(name="friend_ship")
@Data
public class MemberFriend extends BaseTimeEntity{

    @Id
    @Column(name="f_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fnum;

    @JoinColumn(name="Member_id")
//    @ManyToOne(fetch = FetchType.LAZY)// NullPointException 에러발생
    private String loginId;

    @JoinColumn(name="Member_id")
//    @ManyToOne(fetch = FetchType.LAZY)
    private String friendId;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'STANDBY'")
    private FriendShip type = FriendShip.STANDBY;
}