package com.miso;

import com.miso.dao.PlayerDAO;
import com.miso.entity.Player;
import com.miso.enums.Category;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Component
public class FillDatabase implements CommandLineRunner {

    private static final String FILE_NAME = "C:/Users/miso/Documents/TestExcel.xlsx";

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public void run(String... args) throws Exception {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                List<Player> playerList = new ArrayList<>();
                Sheet datatypeSheet = workbook.getSheetAt(i);
                String category = workbook.getSheetName(i);

                Iterator<Row> iterator = datatypeSheet.iterator();
                //read column titles
                iterator.next();
                Row currentRow;
                int count;

                while (iterator.hasNext()) {
                    Player p = new Player();
                    count = 0;
                    //ignore first row i.e culumn names
                    currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();


                    while (cellIterator.hasNext()) {
                        p.setCategory(Category.valueOf(category));
                        Cell currentCell = cellIterator.next();

                        switch (count) {
                            case 0:
                                //System.out.println(currentCell.getStringCellValue());
                                p.setName(currentCell.getStringCellValue());
                                break;
                            case 1:
                                p.setSurname(currentCell.getStringCellValue());
                                break;
                            case 2:
                                p.setDebt(currentCell.getNumericCellValue());
                        }
                        count++;
                    }
                    playerList.add(p);
                    System.out.println();

                }
                for (Player p : playerList) {
                    //System.out.println(p + " " + i);
                    playerDAO.create(p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
