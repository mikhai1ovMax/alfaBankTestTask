package com.maximmikhailov.alfabanktesttask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maximmikhailov.alfabanktesttask.client.CurrencyClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyService {

    private CurrencyClient currencyClient;

    @Value("${openexchangerates.AppId}")
    private String appId;

    @Value("${openexchangerates.baseCurrency}")
    private String base;

    @Autowired
    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    public boolean isCurrentRateBigger(String currency){
        if(getCurrentRate(currency) >= getYesterdayRate(currency))
            return true;
        return false;
    }

    private Double getCurrentRate(String currency){
        return extractCurrencyFromJSON(currencyClient.getCurrentRate(appId, base), currency);
    }

    private Double getYesterdayRate(String currency){
        String date = LocalDate.now().minus(Period.ofDays(1)).format(DateTimeFormatter.ISO_LOCAL_DATE);
        return extractCurrencyFromJSON(currencyClient.getOldRate(date, appId, base), currency);
    }

    @SneakyThrows
    private Double extractCurrencyFromJSON(String JSONWithCurrencies, String currency){
        return new ObjectMapper().readTree(JSONWithCurrencies).path("rates").path(currency).asDouble();
    }

}
