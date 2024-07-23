import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseadorJson {

    private static ArrayList<Movie> filmes;

    //Retorna valores inteiros do json. Exemplo "Original_title":"nome do filme"
    public static ArrayList<String> parseJson(Pattern padrao, String json){
        Matcher matcher = padrao.matcher(json);
        ArrayList<String> valoresProcurados = new ArrayList<>();
        while (matcher.find()) {
            valoresProcurados.add(matcher.group());
        }
        return valoresProcurados;
    }

    public static ArrayList<String> parseValor(ArrayList<String> arrayValoresDoTitulo){
        ArrayList<String> nomesFilmes = new ArrayList<>();
        //"title":"dead pool"
        //Busca o inicio da string


        for(String i : arrayValoresDoTitulo){
            int inicioString = i.indexOf(":")+2;
            int finalString =  i.length()-1;

            if(i.contains("vote_average")){
                inicioString = i.indexOf(":")+1;
                finalString = i.length();
            }

            String nome = i.substring(inicioString, finalString);
            nomesFilmes.add(nome);
        }

        return nomesFilmes;
    }

    public static ArrayList<Movie> ArrayDeFilmes (String json){
        ArrayList <Movie> arrayDeMovies = new ArrayList<>();

        Pattern padraoTituloFilme = Pattern.compile("\"title\":\"(.*?)\"");
        Pattern padraoPosterFilme = Pattern.compile("\"poster_path\":\"(/.*?)\"");
        Pattern padraoNotaFilme = Pattern.compile("\"vote_average\":[0-9].[0-9]");
        Pattern padraoAnoLancamentoFilme = Pattern.compile("\"release_date\":\"[0-9][0-9][0-9][0-9]" +
                "-[0-9][0-9]-[0-9][0-9]\"");


        ArrayList<String> listaFilmes = parseJson(padraoTituloFilme,json);
        System.out.println(listaFilmes);
        ArrayList<String> listaFilmesNomes = parseValor(listaFilmes);
        System.out.println(listaFilmesNomes);

        ArrayList<String> listaPoster = parseJson(padraoPosterFilme,json);
        System.out.println(listaPoster);
        ArrayList<String> listaPosterNome = parseValor(listaPoster);
        System.out.println(listaPosterNome);

        ArrayList<String> listaNota = parseJson(padraoNotaFilme,json);
        System.out.println(listaNota);
        ArrayList<String> listaFilmeNota = parseValor(listaNota);
        System.out.println(listaFilmeNota);

        ArrayList<String> listaLancamento = parseJson(padraoAnoLancamentoFilme,json);
        System.out.println(listaLancamento);
        ArrayList<String> listaLancamentoData = parseValor(listaLancamento);
        System.out.println(listaLancamentoData);

        for(int i = 0; i < listaFilmes.size(); i++){
            try {
                Movie filme = new Movie();
                filme.setTitulo(listaFilmesNomes.get(i));
                filme.setUrlPoster(listaPosterNome.get(i));
                filme.setNota(Double.parseDouble(listaFilmeNota.get(i)));
                filme.setDataLancamento(listaLancamentoData.get(i));
                arrayDeMovies.add(filme);
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return arrayDeMovies;
    }

    public static ArrayList<Movie> getArrayFilmes(String json){
        //Extrair o valor de "original_title", "poster_path", "release_date" e "vote_average"
        filmes = ParseadorJson.ArrayDeFilmes(json);
        return filmes;
    }
}
