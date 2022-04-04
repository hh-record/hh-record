package com.hh.record.entity.record;

import com.hh.record.entity.BaseEntity;
import com.hh.record.entity.member.Member;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class RecordEmotion extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emotion_seq", nullable = false)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_seq", foreignKey = @ForeignKey(name = "record_fk"))
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "write_member_seq", foreignKey = @ForeignKey(name = "write_member_fk"))
    private Member writeMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq", foreignKey = @ForeignKey(name = "member_fk"))
    private Member member;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    public RecordEmotion(Record record, Member writeMember, Member member, Emotion emotion) {
        this.record = record;
        this.writeMember = writeMember;
        this.member = member;
        this.emotion = emotion;
    }

}