import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTMLGenerator {
    private File file;
    private PrintWriter caneta;

    public HTMLGenerator(String nomeArquivo) throws FileNotFoundException {
        String nome = "./".concat(nomeArquivo);
        file = new File(nome);
        this.caneta = new PrintWriter(this.file);
    }

    public void docHTMLHeaderBegin() {
        String docHTML ="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                " <link rel=\"stylesheet\" href=\"/style.css\">\n" +
                "</head>\n" +
                "<body>";

        caneta.write(docHTML);
    }

    public void contentHtml(String content){
        caneta.write(content);
    }

    public void docHTMLHeaderEnd() {
        String docHTML = "</body>\n" +
                "</html>";
        caneta.write(docHTML);
    }

    public void closeHtml(){
        caneta.close();
    }




}
