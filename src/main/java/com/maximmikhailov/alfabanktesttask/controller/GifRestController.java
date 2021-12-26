package com.maximmikhailov.alfabanktesttask.controller;

import com.maximmikhailov.alfabanktesttask.service.CurrencyService;
import com.maximmikhailov.alfabanktesttask.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping(value = "showGif/{currency}")
    public RedirectView getGif(@PathVariable(name = "currency") String currency){
        String url = gifService.getGifURL(currency);
        return new RedirectView(url);
    }

}
