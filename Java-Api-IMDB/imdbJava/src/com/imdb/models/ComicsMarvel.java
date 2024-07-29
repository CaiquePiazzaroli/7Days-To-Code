package com.imdb.models;

import com.imdb.superclass.Titulo;

public class ComicsMarvel extends Titulo {
    public ComicsMarvel(){
        padroesDeBusca.add("\"title\":\"(.*?)\"");
        padroesDeBusca.add("\"images\":(.*?),");
        padroesDeBusca.add("\"prices\":[{\"type\":\"printPrice\",\"price\":[0-9]}],");
        padroesDeBusca.add("\"dates\":(.*?),\"date\":\"(.*?)\"");
    }

}
