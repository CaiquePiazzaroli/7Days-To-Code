package com.imdb.models;

import com.imdb.superclass.Parser;

public class ParserMovieTmdb extends Parser {

    public ParserMovieTmdb(String json){
        super.json = json;
        super.title = new MovieTmdb();
    }

}
