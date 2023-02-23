package com.portfolio.blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.FriendShip;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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