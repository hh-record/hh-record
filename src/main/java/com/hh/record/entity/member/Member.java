package com.hh.record.entity.member;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import com.hh.record.entity.BaseEntity;
import com.hh.record.entity.medal.Medal;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@TypeDef(name = "json", typeClass = JsonType.class)
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

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Book> books;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Medal> medalList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberFollow> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "targetMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberFollow> targetMemberList = new ArrayList<>();

    @Builder
    public Member(String id, String userName, String email, String password, String phoneNumber,
                  boolean isPrivate, String profileImgUrl, MemberRole roleSet, MemberProvider provider, List<Book> books) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isPrivate = isPrivate;
        this.profileImgUrl = profileImgUrl;
        this.roleSet = roleSet;
        this.provider = provider;
        this.books = books;
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
                    throw new ValidationException("이미 팔로우를 하고 있는 사람입니다.");
                });
        MemberFollow memberFollow = MemberFollow.newFollow(this, targetMember);
        this.targetMemberList.add(memberFollow);
    }

    public void unfollowMember(Member targetMember) {
        MemberFollow memberFollow = this.findFollowByMemberId(targetMember)
                .orElseThrow(() -> new NotFoundException(String.format("팔로우를 하지 않은 사람 %s 입니다.", targetMember.getId())));
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
