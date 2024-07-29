package com.imdb.models;

import com.imdb.superclass.Parser;
import com.imdb.superclass.Titulo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserComicsMarvel extends Parser {

    public ParserComicsMarvel(String json){
        super.json = json;
        super.title = new com.imdb.models.ComicsMarvel();
    }

    @Override
    public ArrayList<Titulo> ArrayDeTitles() {
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
                this.title.setTitulo(listTitlesName.get(i));
                this.title.setUrlPoster(listPosterName.get(i));

                //Verificando se a list está vazia
                if(listTitleAverage.isEmpty()){
                    this.title.setNota(0.00);
                }else {
                    this.title.setNota(Double.parseDouble(listTitleAverage.get(i)));
                }

                this.title.setDataLancamento(listaTitleDate.get(i));
                arrayDeTitulos.add(this.title);
                this.title = new Titulo();
            } catch (Exception e){
                System.out.println("Errro:" + e);
            }
        }

        return arrayDeTitulos;
    }

    @Override
    public ArrayList<String> parseJson(Pattern padrao){
        Matcher matcher = padrao.matcher(this.json);
        ArrayList<String> valoresProcurados = new ArrayList<>();
        while (matcher.find()) {
            //Retorna os valores dentro dos parenteses.
            if(matcher.group(0).contains("title")) {
                valoresProcurados.add(matcher.group(1));
            }else if(matcher.group(0).contains("path")) {
                String[] link = matcher.group(1).split(":", 2);
                valoresProcurados.add(link[1].substring(1,link[1].length()-1).concat(".jpg"));
            } else if(matcher.group(0).contains("dates")) {
                valoresProcurados.add(matcher.group(2).substring(0,10));
            }

            //Obs matcher.group(0) retornaria toda a string
            //System.out.println(matcher.group(0));
        }
        return valoresProcurados;
    }



}
