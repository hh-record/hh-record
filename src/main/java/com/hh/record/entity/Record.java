package com.hh.record.entity;

import com.hh.record.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = "member_seq")
public class Record extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_seq")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq", foreignKey = @ForeignKey(name="record_member_fk"))
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private File file;

    private String thumbnailUrl;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Record(Member member, String thumbnailUrl, String title, String content) {
        this.member = member;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.content = content;
    }

    public void addFile(String fileKey) {
        this.file = File.of(this, fileKey);
    }

    public void changeRecord(String title, String content, String fileUrl) {
        this.title = title;
        this.content = content;
        this.thumbnailUrl = fileUrl;
        file.changeFile(fileUrl);
    }

}
