package com.hh.record.entity.record;

import com.hh.record.entity.BaseEntity;
import com.hh.record.entity.File;
import com.hh.record.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<RecordHashTag> recordHashTagList = new ArrayList<>();

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<RecordEmotion> recordEmotionList = new ArrayList<>();

    private String thumbnailUrl;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "varchar(255) default 'N'")
    private String themeUse;

    @Enumerated(EnumType.STRING)
    private IsPrivate isPrivate;

    @Builder
    public Record(Member member, String thumbnailUrl, String title, String content, IsPrivate isPrivate, String themeUse) {
        this.member = member;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.themeUse = themeUse;
    }

    public void addFile(List<String> fileList) {
        List<File> fileEntityList = fileList.stream().map(file -> File.of(this, file)).collect(Collectors.toList());
        this.fileList.addAll(fileEntityList);
    }

    public void changeRecord(String title, String content, String fileUrl, String themeUse, IsPrivate isPrivate) {
        this.title = title;
        this.content = content;
        this.thumbnailUrl = fileUrl;
        this.themeUse = themeUse;
        this.isPrivate = isPrivate;
    }

    public void changeFile(List<String> fileList) {
        List<File> fileEntityList = fileList.stream().map(file -> File.of(this, file)).collect(Collectors.toList());
        this.fileList.clear();
        this.fileList.addAll(fileEntityList);
    }

    public void addHashTag(List<String> hashTagList) {
        List<RecordHashTag> recordHashTagList = hashTagList.stream().map(hashTag -> RecordHashTag.of(this, hashTag)).collect(Collectors.toList());
        this.recordHashTagList.addAll(recordHashTagList);
    }

}
