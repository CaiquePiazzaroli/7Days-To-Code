import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //Token retirado do site
        String token = "<API TOKEN>";

        //Url da api, também retirado do site
        String url = "https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=5";

        ImdbApiClient imdb = new ImdbApiClient(token,url);
        ArrayList<Movie> listaFilmes = ParseadorJson.getArrayFilmes(imdb.getJson());
        HTMLGenerator html = new HTMLGenerator("index.html");
        html.writeBody(listaFilmes);

    }
}
