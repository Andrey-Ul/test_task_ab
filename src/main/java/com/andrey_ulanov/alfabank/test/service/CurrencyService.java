package com.andrey_ulanov.alfabank.test.service;

import org.springframework.http.ResponseEntity;
import com.andrey_ulanov.alfabank.test.dto.CurrencyDTO;

public interface CurrencyService {
    ResponseEntity<CurrencyDTO> getCurrency(String date, String base);
}
