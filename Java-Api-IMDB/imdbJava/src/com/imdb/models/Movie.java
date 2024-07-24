package com.imdb.models;

public class Movie extends Titulo {

    public Movie(){
        padroesDeBusca.add("\"original_title\":\"(.*?)\"");
        padroesDeBusca.add("\"poster_path\":\"(/.*?)\"");
        padroesDeBusca.add("\"vote_average\":[0-9].[0-9]");
        padroesDeBusca.add("\"release_date\":\"[0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9]\"");
    }

}
