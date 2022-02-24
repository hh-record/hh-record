package com.hh.record.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long seq;

    @Column(name = "member_id")
    private String id;

    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    @Builder
    public Member(String id, String userName, String email, String password, String phoneNumber, List<MemberRole> roleSet) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roleSet = roleSet;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> roleSet = new ArrayList<>();

    public void changeMemberInfo(String userName, String email, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public static Member newMember(String email) {
        return Member.builder()
                .email(email)
                .roleSet(Collections.singletonList(MemberRole.USER))
                .build();
    }

}
