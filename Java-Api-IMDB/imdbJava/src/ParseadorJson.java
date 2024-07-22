import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseadorJson {

    public static ArrayList<String> parseJson(Pattern padrao, String json){
        Matcher matcher = padrao.matcher(json);
        ArrayList<String> valoresProcurados = new ArrayList<>();
        while (matcher.find()) {
            valoresProcurados.add(matcher.group());
        }
        return valoresProcurados;
    }

    public static ArrayList<String> parseValor(ArrayList<String> arrayTitulosOrPoster, String nomeValor){
        ArrayList<String> nomesFilmes = new ArrayList<>();

        //Busca o inicio da string
        int inicioString = nomeValor.length();

        for(String i : arrayTitulosOrPoster){
            int finalString =  i.length()-1;
            if(nomeValor.contains("vote_average")){
                finalString = i.length();
            }
            String nome = i.substring(inicioString, finalString);
            nomesFilmes.add(nome);
        }
        return nomesFilmes;
    }

    public static ArrayList<Movie> ArrayDeFilmes (String json){
        ArrayList <Movie> arrayDeMovies = new ArrayList<>();

        Pattern padraoTituloFilme = Pattern.compile("\"original_title\":\"(.*?)\"");
        Pattern padraoPosterFilme = Pattern.compile("\"poster_path\":\"(/.*?)\"");
        Pattern padraoNotaFilme = Pattern.compile("\"vote_average\":[0-9].[0-9]");
        Pattern padraoAnoLancamentoFilme = Pattern.compile("\"release_date\":\"[0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9]\"");


        ArrayList<String> listaFilmes = parseJson(padraoTituloFilme,json);
        ArrayList<String> listaFilmesNomes = parseValor(listaFilmes, "\"original_title\":\"");
        //System.out.println(listaFilmesNomes);

        ArrayList<String> listaPoster = parseJson(padraoPosterFilme,json);
        ArrayList<String> listaPosterNome = parseValor(listaPoster, "\"poster_path\":\"/");
        //System.out.println(listaPosterNome);

        ArrayList<String> listaNota = parseJson(padraoNotaFilme,json);
        ArrayList<String> listaFilmeNota = parseValor(listaNota, "\"vote_average\":");
        //System.out.println(listaFilmeNota);

        ArrayList<String> listaLancamento = parseJson(padraoAnoLancamentoFilme,json);
        ArrayList<String> listaLancamentoData = parseValor(listaLancamento, "\"release_date\":\"");
        //System.out.println(listaLancamentoData);

        for(int i = 0; i < listaFilmes.size(); i++){
            Movie filme = new Movie();
            filme.setTitulo(listaFilmesNomes.get(i));
            filme.setUrlPoster(listaPosterNome.get(i));
            filme.setNota(Double.parseDouble(listaFilmeNota.get(i)));
            filme.setDataLancamento(listaLancamentoData.get(i));

            arrayDeMovies.add(filme);
        }

        return arrayDeMovies;

    }
}
