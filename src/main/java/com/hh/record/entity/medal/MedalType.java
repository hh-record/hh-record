package com.hh.record.entity.medal;

import com.hh.record.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MedalType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medal_type_id")
    private Long seq;

    // 엠블럼 사진 url
    private String activeEmblem;

    private String inactiveEmblem;

    private String description;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "medalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Medal medal;

}