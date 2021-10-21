
package com.andrey_ulanov.alfabank.test.service.implementation;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.andrey_ulanov.alfabank.test.client.DownloadClient;
import com.andrey_ulanov.alfabank.test.service.DownloadService;

import java.net.URI;

@Service
@Data
public class DownloadServiceImpl implements DownloadService {

    private final DownloadClient downloadClient;

    @Override
    public ResponseEntity<byte[]> getGifByUrl(URI uri) {
        return downloadClient.getGifByUrl(uri);
    }

}

