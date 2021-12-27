package com.maximmikhailov.alfabanktesttask.service;

import com.maximmikhailov.alfabanktesttask.client.GifClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class GifServiceTest {

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private GifClient gifClient;

    @Autowired
    private GifService gifService;


    @Value("${giphy.apiKey}")
    private String apiKey;

    private String richGifURL = "https://media2.giphy.com/media/lptjRBxFKCJmFoibP3/giphy.gif?cid=ecf05e47taxc5388ogb9qm5khgwzomvgepjykhfi4qu3el2x&rid=giphy.gif&ct=g";
    private String brokeGifURL = "https://media3.giphy.com/media/ZGH8VtTZMmnwzsYYMf/giphy.gif?cid=ecf05e47bj4wgpzjzp5tucuhxj5lefgmejvi97d9b2wl474s&rid=giphy.gif&ct=g";
    private String JSONStringWithRichGifURL = "{\"data\":{\"type\":\"gif\",\"images\":{\"original\":{\"url\":\"https://media2.giphy.com/media/lptjRBxFKCJmFoibP3/giphy.gif?cid=ecf05e47taxc5388ogb9qm5khgwzomvgepjykhfi4qu3el2x&rid=giphy.gif&ct=g\"}}}}";
    private String JSONStringWithBrokeGifURL = "{\"data\":{\"type\":\"gif\",\"images\":{\"original\":{\"url\":\"https://media3.giphy.com/media/ZGH8VtTZMmnwzsYYMf/giphy.gif?cid=ecf05e47bj4wgpzjzp5tucuhxj5lefgmejvi97d9b2wl474s&rid=giphy.gif&ct=g\"}}}}";

    @Test
    public void testGetGifURL() {
        given(gifClient.getJsonWithGifURL(apiKey, "rich")).willReturn(JSONStringWithRichGifURL);
        given(gifClient.getJsonWithGifURL(apiKey, "broke")).willReturn(JSONStringWithBrokeGifURL);

        when(currencyService.isCurrentRateBigger("RUB")).thenReturn(true);
        assertEquals(richGifURL, gifService.getGifURL("RUB"));

        when(currencyService.isCurrentRateBigger("RUB")).thenReturn(false);
        assertEquals(brokeGifURL, gifService.getGifURL("RUB"));
    }
}
