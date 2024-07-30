package com.imdb.models;

import com.imdb.superclass.Titulo;

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
        String docHTML =
                """
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Document</title>
                        <link rel="stylesheet" href="/style.css">
                    </head>
                    <body>
                        <h1 class="mainTitle">Top Rated</h1>
                        <main class="containerMain">
                """;
        writer.write(docHTML);
    }

    public static void contentHtml(String content){
        writer.write(content);
    }

    public static void writeEnd() {
        String docHTML = """
                      </main>
                    </body>
                </html>
                """;
        writer.write(docHTML);
    }

    public static void closeHtml(){
        writer.close();
    }

    public void writeBody(ArrayList<Titulo> listaFilmes, String ascOrDes) throws FileNotFoundException {

        HTMLGenerator.writeBegin();
        //for ascending order
        if(ascOrDes.equals("asc")){
            for(int i = 0; i < listaFilmes.size(); i++){
                HTMLGenerator.contentHtml(String.format(
                    """
                    <div class="filme">
                        <h3 class="titulo_filme">%s</h3>
                        <img class="imagem" src="%s"></img>
                        Nota: %s <br>
                        Data de lançamento: %s
                    </div>
                    """,listaFilmes.get(i).getTitle().replace("\\u0026", "&"), listaFilmes.get(i).getUrlPoster(),listaFilmes.get(i).getRating(),listaFilmes.get(i).getRelaseDate()));
            }
        }

        //for when the title is being sorted by descending order
        if(ascOrDes.equals("des")){
            for(int i = listaFilmes.size()-1; i > -1; i--){
                HTMLGenerator.contentHtml(String.format("""
                    <div class="filme">
                        <h3 class="titulo_filme">%s</h3>
                        <img class="imagem" src="%s"></img>
                        Nota: %s <br>
                        Data de lançamento: %s
                    </div>
                    """,listaFilmes.get(i).getTitle().replace("\\u0026", "&"), listaFilmes.get(i).getUrlPoster(),listaFilmes.get(i).getRating(),listaFilmes.get(i).getRelaseDate()));
            }
        }

        HTMLGenerator.writeEnd();
        HTMLGenerator.closeHtml();
    }



}
