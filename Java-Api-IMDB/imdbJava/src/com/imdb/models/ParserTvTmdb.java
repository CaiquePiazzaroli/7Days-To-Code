package com.imdb.models;

import com.imdb.interfaces.Parseable;
import com.imdb.superclass.Titulo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserTvTmdb implements Parseable {

    private ArrayList<Titulo> titulos = new ArrayList<>();
    private String json;
    private TitleTvShowTmdb title = new TitleTvShowTmdb();

    public ParserTvTmdb(String json){
        this.json = json;
    }

    //Return array titles
    @Override
    public ArrayList<Titulo> getArrayTitles(){
        //Extrair o valor de "original_title", "poster_path", "release_date" e "vote_average"
        titulos = ArrayDeTitles();
        return titulos;
    }

    public ArrayList<Titulo> ArrayDeTitles(){

        ArrayList <Titulo> arrayDeTitulos = new ArrayList<>();

        Pattern padraoTitle = Pattern.compile(this.title.title());
        Pattern padraoPoster = Pattern.compile(this.title.urlImage());
        Pattern padraoNota = Pattern.compile(this.title.rating());
        Pattern padraoAnoLancamento = Pattern.compile(this.title.year());

        ArrayList<String> listTitlesName = parseJson(padraoTitle);
        ArrayList<String> listPosterName = parseJson(padraoPoster);
        ArrayList<String> listTitleAverage = parseJson(padraoNota);
        ArrayList<String> listaTitleDate = parseJson(padraoAnoLancamento);

        //Test
        System.out.println(listTitlesName);
        System.out.println(listPosterName);
        System.out.println(listTitleAverage);
        System.out.println(listaTitleDate);

        for(int i = 0; i < listTitlesName.size(); i++){
            try {
                this.title.setTitle(listTitlesName.get(i));
                this.title.setUrlPoster(listPosterName.get(i));
                this.title.setRating(Double.parseDouble(listTitleAverage.get(i)));
                this.title.setRelaseDate(listaTitleDate.get(i));
                arrayDeTitulos.add(this.title);
                this.title = new TitleTvShowTmdb();
            } catch (Exception e){
                System.out.println(e);
            }
        }

        return arrayDeTitulos;
    }

    //Retorna valores inteiros do json. Exemplo "Original_title":"nome do filme"
    public ArrayList<String> parseJson(Pattern padrao){
        Matcher matcher = padrao.matcher(this.json);
        ArrayList<String> valoresProcurados = new ArrayList<>();
        while (matcher.find()) {
            //Retorna os valores dentro dos parenteses.
            if(matcher.group(0).contains("poster_path")){
                valoresProcurados.add("https://image.tmdb.org/t/p/w600_and_h900_bestv2/".concat(matcher.group(1)));
            } else {
                valoresProcurados.add(matcher.group(1));
            }

            //Obs matcher.group(0) retornaria toda a string
            //System.out.println(matcher.group(0));
        }
        return valoresProcurados;
    }




}
