package com.imdb.models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseadorJson {

    private static ArrayList<Titulo> titulos;

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
        ArrayList<String> nomesTitulos = new ArrayList<>();
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
            nomesTitulos.add(nome);
        }

        return nomesTitulos;
    }

    public static Titulo verificarInstancia(String json){
        Titulo titulo = new Movie();

        if(json.contains("\"media_type\":\"tv\"")){
            titulo = new TvShow();
            return titulo;
        }

        return titulo;
    }

    public static ArrayList<Titulo> ArrayDeFilmes (String json){

        ArrayList <Titulo> arrayDeTitulos = new ArrayList<>();

        Titulo titulo = verificarInstancia(json);
        Pattern padraoTitle = Pattern.compile(titulo.title());
        Pattern padraoPoster = Pattern.compile(titulo.urlImage());
        Pattern padraoNota = Pattern.compile(titulo.rating());
        Pattern padraoAnoLancamento = Pattern.compile(titulo.year());

        ArrayList<String> listaFilmes = parseJson(padraoTitle,json);
        //System.out.println(listaFilmes);
        ArrayList<String> listaFilmesNomes = parseValor(listaFilmes);
        //System.out.println(listaFilmesNomes);

        ArrayList<String> listaPoster = parseJson(padraoPoster,json);
        //System.out.println(listaPoster);
        ArrayList<String> listaPosterNome = parseValor(listaPoster);
        //System.out.println(listaPosterNome);

        ArrayList<String> listaNota = parseJson(padraoNota,json);
        //System.out.println(listaNota);
        ArrayList<String> listaFilmeNota = parseValor(listaNota);
        //System.out.println(listaFilmeNota);

        ArrayList<String> listaLancamento = parseJson(padraoAnoLancamento,json);
        //System.out.println(listaLancamento);
        ArrayList<String> listaLancamentoData = parseValor(listaLancamento);
        //System.out.println(listaLancamentoData);

        for(int i = 0; i < listaFilmes.size(); i++){
            try {
                titulo.setTitulo(listaFilmesNomes.get(i));
                titulo.setUrlPoster(listaPosterNome.get(i));
                titulo.setNota(Double.parseDouble(listaFilmeNota.get(i)));
                titulo.setDataLancamento(listaLancamentoData.get(i));
                arrayDeTitulos.add(titulo);
                titulo = verificarInstancia(json);
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return arrayDeTitulos;
    }

    public static ArrayList<Titulo> getArrayFilmes(String json){
        //Extrair o valor de "original_title", "poster_path", "release_date" e "vote_average"
        titulos = ParseadorJson.ArrayDeFilmes(json);
        return titulos;
    }
}
