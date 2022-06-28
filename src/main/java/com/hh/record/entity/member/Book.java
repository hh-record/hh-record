package com.hh.record.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class Book {

    private String id;
    private String title;
    private int price;

    public Book(String title, int price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.price = price;
    }

}
