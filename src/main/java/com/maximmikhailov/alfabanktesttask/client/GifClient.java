package com.maximmikhailov.alfabanktesttask.client;

import com.maximmikhailov.alfabanktesttask.model.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "gif", url = "${giphy.url.randomGif}")
public interface GifClient {

    @GetMapping()
    public GiphyResponse getGif(@RequestParam("api_key") String apiKey,
                                @RequestParam("tag") String tag);

}
