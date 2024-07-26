package com.imdb.superclass;

import com.imdb.interfaces.Parseable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements Parseable {

    private ArrayList<Titulo> titulos = new ArrayList<>();
    protected String json;
    protected Titulo title;

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
                this.title.setTitulo(listTitlesName.get(i));
                this.title.setUrlPoster(listPosterName.get(i));
                this.title.setNota(Double.parseDouble(listTitleAverage.get(i)));
                this.title.setDataLancamento(listaTitleDate.get(i));
                arrayDeTitulos.add(this.title);
                this.title = new Titulo();
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
            valoresProcurados.add(matcher.group(1));

            //Obs matcher.group(0) retornaria toda a string
            //System.out.println(matcher.group(0));
        }
        return valoresProcurados;
    }
}
