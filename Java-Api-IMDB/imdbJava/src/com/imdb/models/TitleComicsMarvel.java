package com.imdb.models;

import com.imdb.superclass.Titulo;

public class TitleComicsMarvel extends Titulo{

    private String pageNumber;

    public TitleComicsMarvel(){
        patterns.add("\"title\":\"(.*?)\"");
        patterns.add("\"images\":(.*?),");
        patterns.add("rating");
        patterns.add("\"dates\":(.*?),\"date\":\"(.*?)\"");
        patterns.add("\"pageCount\":(\\d*+),");
    }

    public String pageNumber(){
        return super.patterns.get(4);
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }
}
