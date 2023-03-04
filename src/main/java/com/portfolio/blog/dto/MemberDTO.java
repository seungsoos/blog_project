package com.portfolio.blog.dto;

import com.portfolio.blog.entity.Member;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@ToString
public class MemberDTO {

    @Length(min = 6,max = 10,message = "아이디는 영문 6자이상 10자이하로 입력해주세요.")
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",message = "영문으로 입력해주세요.")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;
    @Length(min = 3,max = 6,message = "닉네임은 3자이상 6자이하로 입력해주세요.")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;
    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣]*$",message = "이름은 한글로 입력해주세요.")
    private  String name;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 6,max = 10,message = "비밀번호는 6자이상 10자이하로 입력해주세요.")
    private String password;

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO를 -> Entity로 변경
    public Member createMember(){
        return modelMapper.map(this, Member.class);
    }

    //Entity를 -> DTO로 변경
    public static MemberDTO of(Member member){
        return modelMapper.map(member, MemberDTO.class);
    }
}
