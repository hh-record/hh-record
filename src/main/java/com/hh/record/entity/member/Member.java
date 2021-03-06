package com.hh.record.entity.member;

import com.hh.record.config.exception.errorCode.NotFoundException;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long seq;

    @Column(name = "member_id")
    private String id;

    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    private Boolean isPrivate;

    private String profileImgUrl;

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
    public Member(String id, String userName, String email, String password, String phoneNumber,
                  boolean isPrivate, String profileImgUrl, MemberRole roleSet, MemberProvider provider) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isPrivate = isPrivate;
        this.profileImgUrl = profileImgUrl;
        this.roleSet = roleSet;
        this.provider = provider;
    }

    public void changeMemberInfo(String userName, String phoneNumber, boolean isPrivate, String profileImgUrl) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.isPrivate = isPrivate;
        this.profileImgUrl = profileImgUrl;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void followMember(Member targetMember) {
        this.findFollowByMemberId(targetMember)
                .ifPresent(memberFollow -> {
                    throw new ValidationException("?????? ???????????? ?????? ?????? ???????????????.");
                });
        MemberFollow memberFollow = MemberFollow.newFollow(this, targetMember);
        this.targetMemberList.add(memberFollow);
    }

    public void unfollowMember(Member targetMember) {
        MemberFollow memberFollow = this.findFollowByMemberId(targetMember)
                .orElseThrow(() -> new NotFoundException(String.format("???????????? ?????? ?????? ?????? %s ?????????.", targetMember.getId())));
        this.memberList.remove(memberFollow);
    }

    private Optional<MemberFollow> findFollowByMemberId(Member targetMember) {
        return this.memberList.stream().filter(memberFollow -> memberFollow.findFollowByTargetMember(targetMember)).findFirst();
    }

    public static Member newMember(GoogleMemberInfoResponse memberInfoResponse, MemberProvider provider) {
        return Member.builder()
                .id(memberInfoResponse.getEmail())
                .email(memberInfoResponse.getEmail())
                .userName(memberInfoResponse.getName())
                .profileImgUrl(memberInfoResponse.getPicture())
                .roleSet(MemberRole.USER)
                .provider(provider)
                .build();
    }

    public void changeIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

}
