package com.universityteam.flashmemorizer.service;

import lombok.NonNull;

public interface ImageService {
    byte[] convertPathToByte(@NonNull String path);
}
