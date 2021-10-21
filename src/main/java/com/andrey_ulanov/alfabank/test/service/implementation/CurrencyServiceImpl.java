package com.andrey_ulanov.alfabank.test.service.implementation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.andrey_ulanov.alfabank.test.client.CurrencyClient;
import com.andrey_ulanov.alfabank.test.dto.CurrencyDTO;
import com.andrey_ulanov.alfabank.test.service.CurrencyService;

@Service
@Data
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyClient currencyClient;
    private final String API_ID;

    public CurrencyServiceImpl(CurrencyClient currencyClient, @Value("${currency.api_id}") String API_ID) {
        this.currencyClient = currencyClient;
        this.API_ID = API_ID;
    }

    public ResponseEntity<CurrencyDTO> getCurrency(String date, String base) {
        return currencyClient.getCurrency(date, API_ID, base);
    }
}
