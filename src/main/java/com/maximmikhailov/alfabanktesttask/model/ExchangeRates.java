package com.maximmikhailov.alfabanktesttask.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties
public class ExchangeRates {
    private String base;
    private Map<String, Double> rates;
}
