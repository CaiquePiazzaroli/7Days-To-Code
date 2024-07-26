package com.imdb.models;

import com.imdb.superclass.Parser;

public class ParserTvTmdb extends Parser {

    public ParserTvTmdb(String json){
        super.json = json;
        super.title = new TvShowTmdb();
    }

}
