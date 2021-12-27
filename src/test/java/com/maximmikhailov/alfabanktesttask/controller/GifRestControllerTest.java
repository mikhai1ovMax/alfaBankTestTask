package com.maximmikhailov.alfabanktesttask.controller;

import com.maximmikhailov.alfabanktesttask.service.GifService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GifRestControllerTest {

    @MockBean
    private GifService gifService;

    @InjectMocks
    private GifRestController gifRestController;

    @Autowired
    MockMvc mockMvc;

    private String richGifURL = "https://media2.giphy.com/media/lptjRBxFKCJmFoibP3/giphy.gif?cid=ecf05e47taxc5388ogb9qm5khgwzomvgepjykhfi4qu3el2x&rid=giphy.gif&ct=g";

    @Test
    public void testGetGif() throws Exception {
        when(gifService.getGifURL(any())).thenReturn(richGifURL);

        String currency = "RUB";
        mockMvc.perform(
                get("/api/v1/gifs/showGif/{currency}", currency)
        ).andExpect(status().is3xxRedirection());
    }

}
