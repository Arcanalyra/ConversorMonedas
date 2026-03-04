package com.boyerolyra.conversor.service;


import com.google.gson.Gson;
import com.boyerolyra.conversor.model.ExchangeResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {

    private static final String API_KEY = "83870ef37afb21e15add24e0";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public double obtenerTasa(String base, String destino) throws Exception {

        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ExchangeResponse data =
                gson.fromJson(response.body(), ExchangeResponse.class);

        return data.getConversion_rates().get(destino);
    }

}
