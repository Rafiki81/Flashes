package com.rafiki.flashes.model;

import java.io.Serializable;

public class Film implements Serializable {

    private Integer id;
    private String title;
    private String director;
    private String synopsis;
    private Integer runningTime;
    private String genre;
    private Double note;

    public Film() {
    }

    public Film(Integer id, String title, String director, String synopsis, Integer runningTime, String genre, Double note) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.synopsis = synopsis;
        this.runningTime = runningTime;
        this.genre = genre;
        this.note = note;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
