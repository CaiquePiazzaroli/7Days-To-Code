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

    public String getJson() {
        return json;
    }
}
