
package com.andrey_ulanov.alfabank.test.service;

import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface DownloadService {
    ResponseEntity<byte[]> getGifByUrl(URI url);
}

