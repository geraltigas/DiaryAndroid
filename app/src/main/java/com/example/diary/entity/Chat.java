package com.example.diary.entity;


import androidx.core.app.NavUtils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Chat implements Serializable {
    public String user;
    public String realUser;
    public String content;
    public String date;
    public String image;

    public Chat(String user, String realUser, String content, String date,String image) {
        this.user = user; //
        this.realUser = realUser;  //
        this.content = content;  //
        this.date = date;  //
        this.image =image;  //
    }

    public Chat() {
        this.user = null; //
        this.realUser = null;  //
        this.content = null;  //
        this.date = null;  //
        this.image = null;  //
    }

    @Override
    public String toString() {
        return "Chat{" +
                "user='" + user + '\'' +
                ", realUser='" + realUser + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
