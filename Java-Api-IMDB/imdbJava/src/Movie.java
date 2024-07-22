public class Movie {

    private String titulo;
    private String urlPoster;
    private double nota;
    private String dataLancamento;


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
        return "Movie{" +
                "titulo='" + titulo + '\'' +
                ", urlPoster='" + urlPoster + '\'' +
                ", nota=" + nota +
                ", dataLancamento='" + dataLancamento + '\'' +
                '}';
    }
}
