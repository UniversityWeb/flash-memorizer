package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.service.ImageService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final String DEFAULT_EXTENSION = "jpg";

    @Override
    public byte[] convertPathToByte(@NonNull String path) {
        try {
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);
            if (image != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, DEFAULT_EXTENSION, baos);
                return baos.toByteArray();
            }
        } catch (IOException e) {
            log.error("Error occurred while converting image to byte[]:" + e.getMessage());
        }
        return null;
    }
}
