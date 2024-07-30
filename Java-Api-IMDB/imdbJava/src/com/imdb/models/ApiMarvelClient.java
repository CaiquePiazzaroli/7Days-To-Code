package com.imdb.models;

import com.imdb.interfaces.ApiClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class ApiMarvelClient implements ApiClient {


    private String apiUrl;
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String json;

    // Marvel api connection
    public ApiMarvelClient(String apikey, String privateApiKey, String timeStamp, String url) throws URISyntaxException, IOException, InterruptedException {

        //The hash to authenticate
        String hashCode = HashMd5.getMd5(timeStamp + privateApiKey +  apikey);

        //Making the url request
        this.apiUrl = url.concat(
                String.format("&ts=%s&apikey=%s&hash=%s",timeStamp,apikey, hashCode)
        );
        System.out.println(this.apiUrl);
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
