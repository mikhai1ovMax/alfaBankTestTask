package com.maximmikhailov.alfabanktesttask.service;

import com.maximmikhailov.alfabanktesttask.client.CurrencyClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class CurrencyServiceTest {

    @MockBean
    CurrencyClient currencyClient;

    @Autowired
    CurrencyService currencyService;

    @Value("${openexchangerates.AppId}")
    private String appId;

    @Value("${openexchangerates.baseCurrency}")
    private String base;

    private String JSONStringWithBigRates = " { \"timestamp\": 1640516418,\n " +
            "\"base\": \"USD\",\n " +
            "\"rates\": " +
            "{\n \"EUR\": 0.8818,\n " +
            "\"RUB\": 73.6625,\n " +
            "\"USD\": 1\n} \n}";

    private String JSONStringWithSmallRates = " { \"timestamp\": 1640516418,\n " +
            "\"base\": \"USD\",\n " +
            "\"rates\": " +
            "{\n \"EUR\": 0.7818,\n " +
            "\"RUB\": 71.6625,\n " +
            "\"USD\": 1\n} \n}";


    @Test
    public void testIsCurrentRateBigger(){
        String date = LocalDate.now().minus(Period.ofDays(1)).format(DateTimeFormatter.ISO_LOCAL_DATE);
        when(currencyClient.getCurrentRate(appId, base)).thenReturn(JSONStringWithBigRates);
        when(currencyClient.getOldRate(date, appId, base)).thenReturn(JSONStringWithSmallRates);
        assertTrue(currencyService.isCurrentRateBigger("RUB"));
        assertTrue(currencyService.isCurrentRateBigger("EUR"));
    }

    @Test
    public void testIsCurrentRateBiggerWhenCurrentRateIsSmaller(){
        String date = LocalDate.now().minus(Period.ofDays(1)).format(DateTimeFormatter.ISO_LOCAL_DATE);
        when(currencyClient.getCurrentRate(appId, base)).thenReturn(JSONStringWithSmallRates);
        when(currencyClient.getOldRate(date, appId, base)).thenReturn(JSONStringWithBigRates);
        assertFalse(currencyService.isCurrentRateBigger("RUB"));
        assertFalse(currencyService.isCurrentRateBigger("EUR"));
    }

}