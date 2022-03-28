package com.hh.record.entity;

import com.hh.record.entity.record.Record;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_seq", foreignKey = @ForeignKey(name="file_record_fk"))
    private Record record;

    private String fileKey;

    public File(Record record, String fileKey) {
        this.record = record;
        this.fileKey = fileKey;
    }

    public static File of(Record record, String fileKey) {
        return new File(record, fileKey);
    }

}
