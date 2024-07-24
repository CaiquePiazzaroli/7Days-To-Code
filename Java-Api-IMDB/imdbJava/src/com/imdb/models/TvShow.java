package com.imdb.models;

public class TvShow extends Titulo {
    public TvShow(){
        padroesDeBusca.add("\"original_name\":\"(.*?)\"");
        padroesDeBusca.add("\"poster_path\":\"(/.*?)\"");
        padroesDeBusca.add("\"vote_average\":[0-9].[0-9]");
        padroesDeBusca.add("\"first_air_date\":\"[0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9]\"");
    }
}
