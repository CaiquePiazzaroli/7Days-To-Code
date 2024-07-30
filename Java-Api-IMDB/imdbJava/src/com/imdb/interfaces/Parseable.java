package com.imdb.interfaces;

import com.imdb.superclass.Titulo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public interface Parseable {

    ArrayList<Titulo> getArrayTitles();

    ArrayList<Titulo> ArrayDeTitles();

    ArrayList<String> parseJson(Pattern padrao);
}
