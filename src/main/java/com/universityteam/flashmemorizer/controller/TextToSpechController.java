package com.universityteam.flashmemorizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class TextToSpechController {
    @GetMapping("/play-audio")
    public ResponseEntity<byte[]> playAudio(@RequestParam String text) {
        // Logic to fetch audio from VoiceRSS API and return as a byte array
        byte[] audioData = fetchAudioFromVoiceRSS(text);
        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg") // Set the appropriate content type
                .body(audioData);
    }

    private byte[] fetchAudioFromVoiceRSS(String text) {
        // Replace with your actual VoiceRSS API endpoint URL
        String voiceRssApiUrl = "https://api.voicerss.org/";
        String apiKey = "d62e7c8089e446bc84849496b4dc5f5f"; // Replace with your API key

        // Configure the RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(HttpHeaders.USER_AGENT, "YourUserAgent");

        // Build the request body with required parameters
        String requestBody = "key=" + apiKey + "&hl=en-us&src=" + text;

        // Build the RequestEntity
        RequestEntity<String> requestEntity = null;
        try {
            requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, new URI(voiceRssApiUrl));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            // Handle URISyntaxException appropriately
        }

        // Make the request and retrieve the audio data as a byte array
        byte[] audioData = restTemplate.exchange(requestEntity, byte[].class).getBody();

        return audioData;
    }
}
