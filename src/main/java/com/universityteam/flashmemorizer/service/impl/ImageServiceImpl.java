package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.DeckConverter;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] convertPathToByte(String path) {
        byte[] imageBytes = null;
        try {
            // Read the image file into a BufferedImage
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);

            // Check if the image was successfully read
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                imageBytes = baos.toByteArray();
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }
}
