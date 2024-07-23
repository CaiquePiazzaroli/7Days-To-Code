import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //Token retirado do site
        String token = "<Token da API>";

        //Url da api, também retirado do site
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video" +
               "=false&language=en-US&page=1&sort_by=popularity.desc";

        ImdbApiClient imdb = new ImdbApiClient(token,url);

        ArrayList<Movie> listaFilmes = imdb.getArrayFilmes();
        HTMLGenerator html = new HTMLGenerator("index.html");
        html.writeBody(listaFilmes);

    }
}
