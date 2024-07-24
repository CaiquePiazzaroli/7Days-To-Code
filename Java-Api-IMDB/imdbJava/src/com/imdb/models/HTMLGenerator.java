package com.imdb.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HTMLGenerator {
    private File a;
    private static PrintWriter writer;

    public HTMLGenerator(String nomeArquivo) throws FileNotFoundException {
        String nome = "./".concat(nomeArquivo);
        this.a = new File(nome);
        this.writer = new PrintWriter(this.a);
    }

    public static void writeBegin() {
        String docHTML ="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Document</title>\n" +
                "<link rel=\"stylesheet\" href=\"/style.css\">\n" +
                "</head>\n" +
                "<body>" +
                "<h1 class=\"mainTitle\">Top Rated movies</h1>" +
                "\n<main class=\"containerMain\">";
        writer.write(docHTML);
    }

    public static void contentHtml(String content){
        writer.write(content);
    }

    public static void writeEnd() {
        String docHTML = "</main>\n</body>\n" +
                "</html>";
        writer.write(docHTML);
    }


    public static void closeHtml(){
        writer.close();
    }

    public void writeBody(ArrayList<Titulo> listaFilmes) throws FileNotFoundException {
        HTMLGenerator.writeBegin();
        for(int i = 0; i < listaFilmes.size(); i++){
            HTMLGenerator.contentHtml(String.format("""
                    <div class="filme">
                        <h3 class="titulo_filme">%s</h3>
                        <img class="imagem" src="https://image.tmdb.org/t/p/w600_and_h900_bestv2/%s"></img>
                        Nota: %s <br>
                        Data de lançamento: %s
                    </div>
                    """,listaFilmes.get(i).getTitulo(), listaFilmes.get(i).getUrlPoster(),listaFilmes.get(i).getNota(),listaFilmes.get(i).getDataLancamento()));
        }
        HTMLGenerator.writeEnd();
        HTMLGenerator.closeHtml();
    }



}
