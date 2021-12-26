package com.maximmikhailov.alfabanktesttask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maximmikhailov.alfabanktesttask.client.GifClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    private CurrencyService currencyService;
    private GifClient gifClient;

    @Value("${giphy.apiKey}")
    String apiKey;

    @Autowired
    public GifService(CurrencyService currencyService, GifClient gifClient) {
        this.currencyService = currencyService;
        this.gifClient = gifClient;
    }

    public String getGifURL(String currency) {
        if (currencyService.isCurrentRateBigger(currency))
            return extractURL(gifClient.getJsonWithGifURL(apiKey, "rich"));
        return extractURL(gifClient.getJsonWithGifURL(apiKey, "broke"));
    }

    @SneakyThrows
    private String extractURL(String JSONWithGif) {
        return new ObjectMapper().readTree(JSONWithGif).path("data").path("images").path("original").path("url").asText();
    }
}
