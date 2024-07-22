import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.http.HttpRequest.newBuilder;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //Token retirado do site
        String token = "sua chave";

        //Url da api, também retirado do site
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video" +
                "=false&language=en-US&page=1&sort_by=popularity.desc";

        //Criando o cliente
        HttpClient client = HttpClient.newBuilder().build();

        //Criando o request
        HttpRequest request = newBuilder()
                .uri(new URI(url))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        //Criando a response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.statusCode());
        //System.out.println(response.body());
        String json = response.body();
        //System.out.println(json);

        //Extrair o valor de "original_title", "poster_path", "release_date" e "vote_average"
        ArrayList<Movie> filmes = ParseadorJson.ArrayDeFilmes(json);
        System.out.println(filmes);



        //Criando um arquivo index.html

        HTMLGenerator html = new HTMLGenerator("index.html");
        html.docHTMLHeaderBegin();
        for(int i = 0; i < filmes.size(); i++){
            html.contentHtml(String.format("<h3 class=\"titulo\">%s</h3>\n",filmes.get(i).getTitulo()));
            html.contentHtml(String.format("<img class=\"imagem\" src=\"https://image.tmdb.org/t/p/w600_and_h900_bestv2/%s\"></img>\n",
                    filmes.get(i).getUrlPoster()));
            html.contentHtml(String.format("<p class=\"notaFilme\">Nota do filme: %s</p>\n",filmes.get(i).getNota()));
            html.contentHtml(String.format("<p class=\"dataFilme\">Data de lançamento: %s</p>\n",filmes.get(i).getDataLancamento()));
        }
        html.docHTMLHeaderEnd();
        html.closeHtml();
    }



}
