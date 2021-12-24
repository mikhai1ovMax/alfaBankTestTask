package com.maximmikhailov.alfabanktesttask.service;

import com.maximmikhailov.alfabanktesttask.client.GifClient;
import com.maximmikhailov.alfabanktesttask.model.GiphyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifService {

    CurrencyService currencyService;
    GifClient gifClient;

    @Value("${giphy.apiKey}")
    String apiKey;

    @Autowired
    public GifService(CurrencyService currencyService, GifClient gifClient) {
        this.currencyService = currencyService;
        this.gifClient = gifClient;
    }

    public String getGifURL(String currency) {
        if (currencyService.isCurrentRateBigger(currency))
            return gifClient.getGif(apiKey, "rich").getUrl();
        return gifClient.getGif(apiKey, "broke").getUrl();
    }
}
