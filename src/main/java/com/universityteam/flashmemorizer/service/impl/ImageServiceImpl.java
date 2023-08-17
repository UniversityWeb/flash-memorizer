package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.service.ImageService;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] convertPathToByte(String path) {
        byte[] imageBytes = null;
        try {
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);
            if (image != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                imageBytes = baos.toByteArray();
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(ImageServiceImpl.class.getName());
            logger.log(Level.INFO, e.getMessage(), new Exception("Exception at ImageServiceImpl file"));
        }
        return imageBytes;
    }
}
