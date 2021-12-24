package com.maximmikhailov.alfabanktesttask.controller;

import com.maximmikhailov.alfabanktesttask.client.CurrencyClient;
import com.maximmikhailov.alfabanktesttask.model.GiphyResponse;
import com.maximmikhailov.alfabanktesttask.service.CurrencyService;
import com.maximmikhailov.alfabanktesttask.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping(value = "api/v1/gifs")
public class GifRestController {

    CurrencyService currencyService;
    GifService gifService;

    @Autowired
    public GifRestController(CurrencyService currencyService, GifService gifService) {
        this.currencyService = currencyService;
        this.gifService = gifService;
    }

    @GetMapping(value = "getGif/{currency}")
    public RedirectView getGif(@PathVariable(name = "currency") String currency){

        return new RedirectView(gifService.getGifURL(currency));
    }
}
