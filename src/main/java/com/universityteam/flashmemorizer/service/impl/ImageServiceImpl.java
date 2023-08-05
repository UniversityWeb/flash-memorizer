package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] convertPathToByte(String path) {
        try {
            byte[] imageData = Files.readAllBytes(Paths.get(path));
            return imageData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
