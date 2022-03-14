package com.hh.record.entity.member;

import com.hh.record.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberFollow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq", foreignKey = @ForeignKey(name="member_follow_fk"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_member_seq", foreignKey = @ForeignKey(name="member_target_follow_fk"))
    private Member targetMember;

    public MemberFollow(Member member, Member targetMember) {
        this.member = member;
        this.targetMember = targetMember;
    }

    public boolean findFollowByTargetMember(Member targetMember) {
        return Objects.equals(this.targetMember.getSeq(), targetMember.getSeq());
    }

    public static MemberFollow of(Member member, Member targetMember) {
        return new MemberFollow(member, targetMember);
    }

}
