package com.rafiki.flashes.model;

import java.io.Serializable;

public class FilmComment implements Serializable {

    private Integer id;
    private String title;
    private String comment;
    private Integer note;
    private String userName;

    public FilmComment() {
    }

    public FilmComment(Integer id, String title, String comment, Integer note, String userName) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.note = note;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
