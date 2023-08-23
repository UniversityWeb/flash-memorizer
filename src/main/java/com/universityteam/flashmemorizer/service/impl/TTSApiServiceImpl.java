package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.service.TTSApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TTSApiServiceImpl implements TTSApiService {

    private final Logger log = LoggerFactory.getLogger(TTSApiServiceImpl.class);

    @Value("${api.voiceRssApiUrl}")
    private String voiceRssApiUrl;

    @Value("${api.voiceRssApiKey}")
    private String voiceRssApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public byte[] fetchAudio(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(HttpHeaders.USER_AGENT, "YourUserAgent");

        String requestBody = "key=" + voiceRssApiKey + "&hl=en-us&src=" + text;

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<byte[]> responseEntity = restTemplate
                    .exchange(voiceRssApiUrl, HttpMethod.POST, requestEntity, byte[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching audio from TTS service: {}", e.getMessage());
            return null;
        }
    }
}
