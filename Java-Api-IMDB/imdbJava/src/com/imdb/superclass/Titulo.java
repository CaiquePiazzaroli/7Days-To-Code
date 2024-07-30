package com.imdb.superclass;
import com.imdb.interfaces.Content;
import com.imdb.models.TitleMovieTmdb;

import java.util.ArrayList;
import java.util.List;

public class Titulo implements Content, Comparable<Titulo> {

    private String title;
    private String urlPoster;
    private double rating;
    private String relaseDate;
    protected List<String> patterns = new ArrayList<>();

    @Override
    public String title() {
        return patterns.get(0);
    }

    @Override
    public String urlImage() {
        return patterns.get(1);
    }

    @Override
    public String rating() {
        return patterns.get(2);
    }

    @Override
    public String year() {
        return patterns.get(3);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRelaseDate(String relaseDate) {
        this.relaseDate = relaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public double getRating() {
        return rating;
    }

    public String getRelaseDate() {
        return relaseDate;
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "titulo='" + title + '\'' +
                ", urlPoster='" + urlPoster + '\'' +
                ", nota=" + rating +
                ", dataLancamento='" + relaseDate + '\'' +
                ", padroesDeBusca=" + patterns +
                '}';
    }

    @Override
    public int compareTo(Titulo t) {
        if(this.getRating() < t.getRating()){
            return -1;
        }
        if(this.getRating() > t.getRating()){
            return 1;
        }
        return 0;
    }
}
