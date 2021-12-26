package com.maximmikhailov.alfabanktesttask.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency", url = "${openexchangerates.url}")
public interface CurrencyClient {


    @GetMapping(value = "latest.json")
    String getCurrentRate(@RequestParam("app_id") String appId,
                                 @RequestParam("base") String base);

    @GetMapping(value = "historical/{date}.json")
    String getOldRate(@PathVariable("date") String localDate,
                                                     @RequestParam("app_id") String appId,
                                                     @RequestParam("base") String base);

}
