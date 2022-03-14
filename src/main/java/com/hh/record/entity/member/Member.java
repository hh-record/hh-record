package com.hh.record.entity.member;

import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import com.hh.record.entity.BaseEntity;
import com.hh.record.entity.medal.Medal;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Enumerated(EnumType.STRING)
    private MemberRole roleSet;

    @Enumerated(EnumType.STRING)
    private MemberProvider provider;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Medal> medalList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberFollow> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "targetMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberFollow> targetMemberList = new ArrayList<>();

    @Builder
    public Member(String id, String userName, String email, String password, String phoneNumber, MemberRole roleSet, MemberProvider provider) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roleSet = roleSet;
        this.provider = provider;
    }

    public void changeMemberInfo(String userName, String email, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void followMember(Member targetMember) {
        this.findFollowByMemberId(targetMember)
                .ifPresent(memberFollow -> {
                    throw new ValidationException("이미 팔로우를 하고 있는 사람입니다.");
                });
        MemberFollow memberFollow = MemberFollow.of(this, targetMember);
        this.targetMemberList.add(memberFollow);
    }

    private Optional<MemberFollow> findFollowByMemberId(Member targetMember) {
        Optional<MemberFollow> first = this.memberList.stream().filter(memberFollow -> memberFollow.findFollowByTargetMember(targetMember)).findFirst();
        System.out.println("first = " + first);
        return first;
    }

    public static Member newMember(GoogleMemberInfoResponse memberInfoResponse, MemberProvider provider) {
        return Member.builder()
                .id(memberInfoResponse.getEmail())
                .email(memberInfoResponse.getEmail())
                .userName(memberInfoResponse.getName())
                .roleSet(MemberRole.USER)
                .provider(provider)
                .build();
    }

}
