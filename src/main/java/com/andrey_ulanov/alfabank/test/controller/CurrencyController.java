package com.andrey_ulanov.alfabank.test.controller;

import com.andrey_ulanov.alfabank.test.service.GifForCurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyController {

    private final GifForCurrencyRateService gif;

    @GetMapping("/gif")
    public ResponseEntity<byte[]> getGifByCurrency(@RequestParam("currency") String currency) {
        return gif.getGifForCurrencyRate(currency);
    }

    // При запросе без параметров будем возвращать данные по рублю
    @GetMapping("/*")
    public ResponseEntity<byte[]> getGifByCurrency() {
        return gif.getGifForCurrencyRate("RUB");
    }
}
