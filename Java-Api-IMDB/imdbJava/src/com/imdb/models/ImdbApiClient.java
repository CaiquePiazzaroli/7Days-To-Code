package com.imdb.models;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class ImdbApiClient {

    private String apiToken;
    private String apiUrl;
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String json;

    //TBDB api connection
    public ImdbApiClient(String token, String apiUrl) throws URISyntaxException, IOException, InterruptedException {

        //Criando o cliente
        this.apiUrl = apiUrl;
        this.client = HttpClient.newBuilder().build();

        //Criando o request
        this.request =  newBuilder()
                .uri(new URI(this.apiUrl))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        //Criando a response
        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
        this.json = this.response.body();
    }

    // Marvel api connection
    public ImdbApiClient(String apikey, String privateApiKey, String timeStamp, String url) throws URISyntaxException, IOException, InterruptedException {

        //The hash to authenticate
        String hashCode = HashMd5.getMd5(timeStamp + privateApiKey +  apikey);

        //Making the url request
        this.apiUrl = url.concat(
                String.format("&ts=%s&apikey=%s&hash=%s",timeStamp,apikey, hashCode)
        );

        this.client = HttpClient.newBuilder().build();
        this.request =  newBuilder()
                .uri(new URI(this.apiUrl))
                .GET()
                .build();

        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
        this.json = this.response.body();
    }

    public String getJson() {
        return json;
    }
}
