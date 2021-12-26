package com.maximmikhailov.alfabanktesttask.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "gif", url = "${giphy.url.randomGif}")
public interface GifClient {

    @GetMapping()
    String getJsonWithGifUrl(@RequestParam("api_key") String apiKey,
                                @RequestParam("tag") String tag);

}
