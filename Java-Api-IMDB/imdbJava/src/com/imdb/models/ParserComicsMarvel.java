package com.imdb.models;
import com.imdb.interfaces.Parseable;
import com.imdb.superclass.Titulo;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserComicsMarvel implements Parseable {

    private ArrayList<Titulo> titulos = new ArrayList<>();
    private String json;
    private TitleComicsMarvel comic = new TitleComicsMarvel();

    public ParserComicsMarvel(String json){
        this.json = json;
    }

    @Override
    public ArrayList<Titulo> getArrayTitles(){
        //Extrair o valor de "original_title", "poster_path", "release_date" e "vote_average"
        titulos = ArrayDeTitles();
        return titulos;
    }

    @Override
    public ArrayList<Titulo> ArrayDeTitles() {
        ArrayList <Titulo> arrayDeTitulos = new ArrayList<>();

        Pattern patternTitle = Pattern.compile(comic.title());
        Pattern patternPoster = Pattern.compile(comic.urlImage());
        Pattern patternRatings = Pattern.compile(comic.rating());
        Pattern patternDateReleased = Pattern.compile(comic.year());
        Pattern patternPageNumber = Pattern.compile(comic.pageNumber());

        ArrayList<String> listTitlesName = parseJson(patternTitle);
        ArrayList<String> listPosterName = parseJson(patternPoster);
        ArrayList<String> listRatings = parseJson(patternRatings);
        ArrayList<String> listTitleDate = parseJson(patternDateReleased);
        ArrayList<String> listPageNumber = parseJson(patternPageNumber);

        //Test
        System.out.println(listTitlesName);
        System.out.println(listPosterName);
        System.out.println(listRatings);
        System.out.println(listTitleDate);
        System.out.println(listPageNumber);

        for(int i = 0; i < listTitlesName.size(); i++){
            try {
                this.comic.setTitle(listTitlesName.get(i));
                this.comic.setUrlPoster(listPosterName.get(i));
                //Verificando se a list está vazia
                if(listRatings.isEmpty()){
                    this.comic.setRating(0.00);
                }else {
                    this.comic.setRating(Double.parseDouble(listPageNumber.get(i)));
                }
                this.comic.setRelaseDate(listTitleDate.get(i));
                this.comic.setPageNumber(listPageNumber.get(i));

                arrayDeTitulos.add(this.comic);
                this.comic = new TitleComicsMarvel();

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
            } else if(matcher.group(0).contains("pageCount")){
                valoresProcurados.add(matcher.group(1));
            }
            //Obs matcher.group(0) retornaria toda a string
            //System.out.println(matcher.group(0));
        }
        return valoresProcurados;
    }
}
