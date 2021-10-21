package com.andrey_ulanov.alfabank.test.service;

import org.springframework.http.ResponseEntity;

public interface GifForCurrencyRateService {
    ResponseEntity<byte[]> getGifForCurrencyRate(String currency);
}
