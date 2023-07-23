package com.universityteam.flashmemorizer.service;
import com.universityteam.flashmemorizer.dto.CardDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public interface ImportService {
    List<CardDTO> ImportExelFile(String filePath);
    List<CardDTO> ImportWithSpace(String filePath);
    List<CardDTO> ImportWithEnter(String filePath);
}
