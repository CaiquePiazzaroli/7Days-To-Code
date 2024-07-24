package com.imdb.main;
import com.imdb.models.HTMLGenerator;
import com.imdb.models.ImdbApiClient;
import com.imdb.models.ParseadorJson;
import com.imdb.models.Titulo;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //Token retirado do site
        String token = "yourToken";

        //Url da api, também retirado do site
        String url = "https://api.themoviedb.org/3/trending/tv/day?language=en-US";

        //Chamando a api
        ImdbApiClient imdb = new ImdbApiClient(token,url);

        //Recebendo o array de Titulos
        ArrayList<Titulo> listaTitulos = ParseadorJson.getArrayFilmes(imdb.getJson());

        //Criando o html e escrevendo no html
        HTMLGenerator html = new HTMLGenerator("index.html");
        html.writeBody(listaTitulos);

    }
}
