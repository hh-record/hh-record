package com.hh.record.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = "record_seq")
public class File extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_seq")
    private Long seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_seq")
    private Record record;

    private String fileKey;

    public File(Record record, String fileKey) {
        this.record = record;
        this.fileKey = fileKey;
    }

    public void changeFile(String fileUrl) {
        int idx = fileUrl.indexOf("/USER");
        this.fileKey = fileUrl.substring(idx + 1);
    }

    public static File of(Record record, String fileKey) {
        return new File(record, fileKey);
    }

}
