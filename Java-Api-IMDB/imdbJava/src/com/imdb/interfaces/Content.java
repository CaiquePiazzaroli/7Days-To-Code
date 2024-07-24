package com.imdb.interfaces;

import com.imdb.models.Titulo;

public interface Content {
    String title();
    String urlImage();
    String rating();
    String year();

    int compareTo(Titulo t);
}
