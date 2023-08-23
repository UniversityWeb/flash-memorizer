package com.universityteam.flashmemorizer.service;

public interface TTSApiService {
    byte[] fetchAudio(String text);
}
