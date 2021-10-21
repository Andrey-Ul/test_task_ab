package com.andrey_ulanov.alfabank.test.service.implementation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.andrey_ulanov.alfabank.test.dto.CurrencyDTO;
import com.andrey_ulanov.alfabank.test.dto.GifDTO;
import com.andrey_ulanov.alfabank.test.exception.NotExistCurrencyCode;
import com.andrey_ulanov.alfabank.test.exception.NotCorrectCurrencyCode;
import com.andrey_ulanov.alfabank.test.service.CurrencyService;
import com.andrey_ulanov.alfabank.test.service.DownloadService;
import com.andrey_ulanov.alfabank.test.service.GifForCurrencyRateService;
import com.andrey_ulanov.alfabank.test.service.GifService;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Data
@Slf4j(topic = "GifOnCurrencyExchangeRateService")
@Service
public class GifForCurrencyRateServiceImpl implements GifForCurrencyRateService {

    private final GifService gifService;
    private final CurrencyService currencyService;
    private final DownloadService downloadService;

    public ResponseEntity<byte[]> getGifForCurrencyRate(String currency) {

        log.info("Начало работы сервиса");

        if (!isCorrectCurrencyCode(currency)) {
            log.error("Неверно указан код валюты: {}", currency);
            throw new NotCorrectCurrencyCode("Неверно указан код валюты: " + currency);
        }

        log.info("Введенный код валюты: {}", currency);

        String todayDate = getFormattedDateFromCurrent(0);
        String yesterdayDate = getFormattedDateFromCurrent(1);

        log.info("Сегодняшняя дата: {}", todayDate);
        log.info("Вчерашняя дата: {}", yesterdayDate);

        double todayRate = getCurrencyRateToUSDByDate(todayDate, currency);
        double yesterdayRate = getCurrencyRateToUSDByDate(yesterdayDate, currency);

        String tagForSearchGif = (todayRate < yesterdayRate) ? "rich" : "broke";

        log.info("Тэг поиска gif для валюты {} с текущим курсом {} и вчерашним курсом {}: {}",
                 currency, todayRate, yesterdayRate, tagForSearchGif);

        URI gifURI = URI.create(getGifUrlByTag(tagForSearchGif));

        return downloadService.getGifByUrl(gifURI);

    }

    private boolean isCorrectCurrencyCode(String currency) {
        return currency.matches("[A-Za-z]{3}");
    }

    private String getFormattedDateFromCurrent(int countDaysFromCurrent) {

        LocalDateTime dateTime = LocalDateTime.now().minusDays(countDaysFromCurrent);
        String dateFromCurrent = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(dateTime);

        return dateFromCurrent;

    }

    private double getCurrencyRateToUSDByDate(String date, String currency) {

        CurrencyDTO currencyDTO = currencyService.getCurrency(date, "USD").getBody();

        if (currencyDTO.getRates().get(currency.toUpperCase()) == null) {
            throw new NotExistCurrencyCode("Валюта с кодом " + currency + " не существует!");
        }

        double rate = currencyDTO.getRates().get(currency.toUpperCase());

        log.info("Курс {} на {} к USD: {}", currency, date, rate);

        return rate;

    }

    private String getGifUrlByTag(String tagForSearchGif) {

        GifDTO gifDTO = gifService.getGifResponse(tagForSearchGif).getBody();
        String url = String.valueOf(Objects.requireNonNull(gifDTO).getData().get("image_original_url"));

        log.info("Сформированный URL gif: {}", url);

        return url;

    }
}


