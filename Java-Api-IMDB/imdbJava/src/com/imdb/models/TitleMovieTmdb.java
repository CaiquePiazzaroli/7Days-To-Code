package com.imdb.models;

import com.imdb.superclass.Titulo;

public class TitleMovieTmdb extends Titulo{
    public TitleMovieTmdb(){
        patterns.add("\"original_title\":\"(.*?)\"");
        patterns.add("\"poster_path\":\"(/.*?)\"");
        patterns.add("\"vote_average\":([0-9].[0-9])");
        patterns.add("\"release_date\":\"([0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9])\"");
    }
}
