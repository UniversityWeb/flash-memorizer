package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.service.TTSApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TextToSpeechController {

    @Autowired
    private TTSApiService ttsService;

    @GetMapping("/play-audio")
    public ResponseEntity<byte[]> playAudio(@RequestParam String text) {
        byte[] audioData = ttsService.fetchAudio(text);
        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(audioData);
    }
}
