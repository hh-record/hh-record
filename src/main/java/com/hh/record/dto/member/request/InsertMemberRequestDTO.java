package com.hh.record.dto.member.request;

import com.hh.record.entity.member.Book;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsertMemberRequestDTO {

    @NotBlank(message = "회원 아이디를 넣어주세요.")
    private String id;

    @NotBlank(message = "회원 이름을 적어주세요.")
    private String userName;

    @Email(message = "알맞지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "회원 비밀번호를 넣어주세요.")
    private String password;

    private boolean isPrivate;

    private String profileImgUrl;

    private List<Book> books;

    public static InsertMemberRequestDTO testInstance(String id, String userName, String email, String phoneNumber,
                                                      String password, boolean isPrivate, String profileImgUrl) {
        return new InsertMemberRequestDTO(id, userName, email, phoneNumber, password, isPrivate, profileImgUrl, null);
    }

    public Member dtoToEntity(String encodedPassword) {
        return Member.builder()
                .id(id)
                .email(email)
                .userName(userName)
                .phoneNumber(phoneNumber)
                .password(encodedPassword)
                .isPrivate(isPrivate)
                .profileImgUrl(profileImgUrl)
                .roleSet(MemberRole.USER)
                .provider(MemberProvider.LOCAL)
                .books(books)
                .build();
    }

}
