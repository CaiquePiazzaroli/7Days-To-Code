package com.imdb.main;
import com.imdb.models.*;
import com.imdb.superclass.Titulo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //Conecting to tmdb api
        String token = "<token tmdb>";

        //Getting Tv Shows
        String urlSeries = "https://api.themoviedb.org/3/tv/popular?language=en-US&page=1";
        ApiTmdbClient imdbTv = new ApiTmdbClient(token,urlSeries);
        ParserTvTmdb parseadorTv = new ParserTvTmdb(imdbTv.getJson());
        ArrayList<Titulo> tvList = parseadorTv.getArrayTitles();

        //Getting movies
        String urlFilmes = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";
        ApiTmdbClient imdbMovies = new ApiTmdbClient(token,urlFilmes);
        ParserMovieTmdb parserMovie = new ParserMovieTmdb(imdbMovies.getJson());
        ArrayList<Titulo> movieList = parserMovie.getArrayTitles();

        //Creating html file
        HTMLGenerator html = new HTMLGenerator("index.html");
        Collections.sort(movieList);
        Collections.sort(tvList);


        //Conecting to marvel API
        String publicApiKey = "<public key>";
        String privateApiKey = "<pricate key>";
        String url = "https://gateway.marvel.com:443/v1/public/comics?" +
                "format=magazine&formatType=comic&noVariants=true" +
                "&hasDigitalIssue=true&orderBy=-onsaleDate&limit=10";
        String timeStamp = "1";
        ApiMarvelClient marvelImdb = new ApiMarvelClient(publicApiKey,privateApiKey,timeStamp, url);
        ParserComicsMarvel parserComics = new ParserComicsMarvel(marvelImdb.getJson());
        ArrayList<Titulo> comicsList = parserComics.getArrayTitles();
        Collections.sort(comicsList);

        //Writing html
//        html.writeBody(comicsList,"des");
//        html.writeBody(tvList,"des");
        html.writeBody(movieList,"des");
    }
}
