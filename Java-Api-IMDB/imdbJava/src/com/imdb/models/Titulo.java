package com.imdb.models;
import com.imdb.interfaces.Content;
import java.util.ArrayList;
import java.util.List;

public class Titulo implements Content {

    private String titulo;
    private String urlPoster;
    private double nota;
    private String dataLancamento;
    protected List<String> padroesDeBusca = new ArrayList<>();

    @Override
    public String title() {
        return padroesDeBusca.get(0);
    }

    @Override
    public String urlImage() {
        return padroesDeBusca.get(1);
    }

    @Override
    public String rating() {
        return padroesDeBusca.get(2);
    }

    @Override
    public String year() {
        return padroesDeBusca.get(3);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public double getNota() {
        return nota;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "titulo='" + titulo + '\'' +
                ", urlPoster='" + urlPoster + '\'' +
                ", nota=" + nota +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", padroesDeBusca=" + padroesDeBusca +
                '}';
    }
}
