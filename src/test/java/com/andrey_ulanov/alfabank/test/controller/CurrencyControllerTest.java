package com.andrey_ulanov.alfabank.test.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверка ответа")
    public void getResponse() throws Exception {
        mockMvc.perform(get("/api/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получение GIF")
    public void getGif() throws Exception {
        mockMvc.perform(get("/api/gif/")
                .param("currency", "USD"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_GIF));
    }

    @Test
    @DisplayName("Неккоректный код валюты")
    public void getGifNotCorrectCode() throws Exception {
        mockMvc.perform(get("/api/gif/")
                .param("currency", "AAA_BBB_CCC"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Валюта с указанным кодом не существует")
    public void getGifNotExistCode() throws Exception {
        mockMvc.perform(get("/api/gif/")
                        .param("currency", "AAA"))
                .andExpect(status().isNotFound());
    }


}

