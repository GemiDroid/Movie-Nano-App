package com.gemi.ahmedgemi.movie_app;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Reviews_Class {


    private String author_name;
    private String content;

    public Reviews_Class(String author_name, String content) {
        this.author_name = author_name;
        this.content = content;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
