package com.gemi.ahmedgemi.movie_app;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Movie_Class {


    private int id;
    private String title;
    private String voting;
    private String synopsis;// OverView
    private String release;
    private String poster_img;



    public Movie_Class(int id, String poster_img, String release, String synopsis, String title, String voting) {
        this.id = id;
        this.poster_img = poster_img;
        this.release = release;
        this.synopsis = synopsis;
        this.title = title;
        this.voting = voting;
    }

    public Movie_Class(String posters) {
        this.poster_img = posters;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_img() {
        return poster_img;
    }

    public void setPoster_img(String poster_img) {
        this.poster_img = poster_img;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }


    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getVoting() {
        return voting;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }
}
