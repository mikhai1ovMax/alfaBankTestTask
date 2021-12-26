package com.maximmikhailov.alfabanktesttask.service;

import com.maximmikhailov.alfabanktesttask.client.CurrencyClient;
import com.maximmikhailov.alfabanktesttask.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyService {

    CurrencyClient currencyClient;

    @Value("${openexchangerates.AppId}")
    String appId;

    @Value("${openexchangerates.baseCurrency}")
    String base;

    @Autowired
    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    public Double getCurrentRate(String currency){
        ExchangeRates exchangeRates = currencyClient.getCurrentRate(appId, base);
        return exchangeRates.getRates().get(currency);
    }

    public Double getYesterdayRate(String currency){
        String date = LocalDate.now().minus(Period.ofDays(1)).format(DateTimeFormatter.ISO_LOCAL_DATE);
        ExchangeRates exchangeRates = currencyClient.getOldRate(date, appId, base);
        return exchangeRates.getRates().get(currency);
    }

    public boolean isCurrentRateBigger(String currency){
        if(getCurrentRate(currency) >= getYesterdayRate(currency))
            return true;
        return false;
    }

}
