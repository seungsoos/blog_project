package com.portfolio.blog.memberdto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Getter
@Setter
@ToString
public class MemberDTO {

    private String id;

    private String nickName;

    private  String name;

    private String password;

}
