package com.imdb.models;

import com.imdb.superclass.Titulo;

public class TvShowTmdb extends Titulo {
    public TvShowTmdb(){
        padroesDeBusca.add("\"original_name\":\"(.*?)\"");
        padroesDeBusca.add("\"poster_path\":\"(/.*?)\"");
        padroesDeBusca.add("\"vote_average\":([0-9].[0-9])");
        padroesDeBusca.add("\"first_air_date\":\"([0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9])\"");
    }
}
