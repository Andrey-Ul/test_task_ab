package com.andrey_ulanov.alfabank.test.service.implementation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.andrey_ulanov.alfabank.test.client.GifClient;
import com.andrey_ulanov.alfabank.test.dto.GifDTO;
import com.andrey_ulanov.alfabank.test.service.GifService;

@Service
@Data
public class GifServiceImpl implements GifService {

    private final GifClient gifClient;
    private final String API_KEY;

    public GifServiceImpl(GifClient gifClient, @Value("${gif.api_key}") String API_KEY) {
        this.gifClient = gifClient;
        this.API_KEY = API_KEY;
    }

    public ResponseEntity<GifDTO> getGifResponse(String tag) {
        return gifClient.getGif(API_KEY, tag);
    }
}
