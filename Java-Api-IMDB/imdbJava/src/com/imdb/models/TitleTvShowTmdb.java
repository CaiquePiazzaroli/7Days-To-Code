package com.imdb.models;

import com.imdb.superclass.Titulo;

public class TitleTvShowTmdb extends Titulo {
    public TitleTvShowTmdb(){
        patterns.add("\"original_name\":\"(.*?)\"");
        patterns.add("\"poster_path\":\"(/.*?)\"");
        patterns.add("\"vote_average\":([0-9].[0-9])");
        patterns.add("\"first_air_date\":\"([0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9])\"");
    }


}
