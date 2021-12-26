package com.maximmikhailov.alfabanktesttask.controller;

import com.maximmikhailov.alfabanktesttask.service.GifService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GifRestControllerTest {

    @MockBean
    private GifService gifService;

    @InjectMocks
    private GifRestController gifRestController;

    @Test
    public void testGetGif() {

    }


}