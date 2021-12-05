package com.example.diary.entity;


import androidx.annotation.NonNull;

import java.io.DataInputStream;
import java.io.Serializable;
import java.util.PrimitiveIterator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Diary implements Serializable {
    public String title; //
    public String content; ///
    public String date; //
    public String image;

    public Diary(String title, String content, String date, String image) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.image = image;
    }

    public Diary(){
        this.title = null;
        this.content = null;
        this.date = null;
        this.image = null;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
