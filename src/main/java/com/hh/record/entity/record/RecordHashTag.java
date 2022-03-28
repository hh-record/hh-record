package com.hh.record.entity.record;

import com.hh.record.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecordHashTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_seq", foreignKey = @ForeignKey(name="hash_tag_record_fk"))
    private Record record;

    private String hashTag;

    public RecordHashTag(Record record, String hashTag) {
        this.record = record;
        this.hashTag = hashTag;
    }

    public static RecordHashTag of(Record record, String hashTag) {
        return new RecordHashTag(record, hashTag);
    }

}
