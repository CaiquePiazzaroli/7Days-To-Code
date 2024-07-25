package com.imdb.main;
import com.imdb.models.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //Token retirado do site
        String token = "<Your_token>";

        //Url da api, também retirado do site
        String url = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";

        //Chamando a api
        ImdbApiClient imdb = new ImdbApiClient(token,url);

        //Conecting to marvel API
//        String publicApiKey = "<public_key>";
//        String privateApiKey = "<private_key>";
//        String url = "https://gateway.marvel.com:443/v1/public/comics?limit=10";
//        String timeStamp = "1";
//        ImdbApiClient imdb = new ImdbApiClient(publicApiKey,privateApiKey,timeStamp, url);

        //Recebendo o array de Titulos

        ArrayList<Titulo> listaTitulos = ParseadorJson.getArrayFilmes(imdb.getJson());

        //Criando o html e escrevendo no html
        HTMLGenerator html = new HTMLGenerator("index.html");
        Collections.sort(listaTitulos);
        html.writeBody(listaTitulos,"des");

    }
}
