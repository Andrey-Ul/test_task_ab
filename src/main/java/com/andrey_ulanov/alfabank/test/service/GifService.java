package com.andrey_ulanov.alfabank.test.service;

import org.springframework.http.ResponseEntity;
import com.andrey_ulanov.alfabank.test.dto.GifDTO;

public interface GifService {
    ResponseEntity<GifDTO> getGifResponse(String tag);
}