package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.service.ImportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportServiceImpl implements ImportService {
    @Override
    public List<CardDTO> ImportExelFile(String filePath)
    {
        var results = new ArrayList<CardDTO>();

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                var cart = new CardDTO();
                Cell cellTerm = row.getCell(0); // First cell in the row
                Cell cellDesc = row.getCell(1); // Second cell in the row
                if (cellTerm != null && cellDesc != null) {
                    cart.setTerm(cellTerm.getStringCellValue());
                    cart.setDesc(cellDesc.getStringCellValue());
                    results.add(cart);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<CardDTO> ImportWithSpace(String inputString)
    {
        var results = new ArrayList<CardDTO>();
        //Todo
        return results;
    }

    @Override
    public List<CardDTO> ImportWithEnter(String inputString)
    {
        var results = new ArrayList<CardDTO>();
        //Todo
        return results;
    }
}
