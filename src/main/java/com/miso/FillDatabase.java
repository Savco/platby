package com.miso;

import com.miso.dao.PlanDAO;
import com.miso.dao.PlayerDAO;
import com.miso.dao.TransactionDAO;
import com.miso.entity.*;
import com.miso.enums.Category;
import com.miso.enums.PlanType;
import com.miso.enums.TransactionState;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class FillDatabase implements CommandLineRunner {

    private static final String FILE_NAME = "C:/Users/miso/Documents/FKM_platby10.6.2019.xlsx";

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private PlanDAO planDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public void run(String... args) throws Exception {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            int numberOfSheets = workbook.getNumberOfSheets();

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

            for (int i = 0; i < numberOfSheets; i++) {
                List<Player> playerList = new ArrayList<>();
                List<Plan> planList = new ArrayList<>();
                Sheet datatypeSheet = workbook.getSheetAt(i);
                StringBuilder category = new StringBuilder(workbook.getSheetName(i));
                if (category.charAt(0) != 'U') continue;
                category.deleteCharAt(1);

                //CellReference cr = new CellReference("A1");
                //Row currentRow = datatypeSheet.getRow(cr.getRow());
                Cell nameCell;
                Cell surnameCell;

                Cell[] monthCell = new Cell[6];
                Comment[] monthComment = new Comment[6];
                List<Set<Date>> dates = new ArrayList<>(6);
                Double[] payments = new Double[6];

                Cell debtCell;
                Cell rowFirstCell;

                Iterator<Row> iterator = datatypeSheet.iterator();
                //read column titles
                iterator.next();
                Row currentRow;
                //boolean doneWithSheet = false;

                while (iterator.hasNext()) {
                    currentRow = iterator.next();
                    rowFirstCell= currentRow.getCell(0);

                    //System.out.println(rowFirstCell);
                    if (isCellEmpty(rowFirstCell)) break;

                    Player p = new Player();
                    Plan plan;

                    if ((category.toString().equals(Category.U6.toString())) || (category.toString().equals(Category.U7.toString()))){
                        plan = new Plan(PlanType.REDUCED);
                    }
                    else {
                        plan = new Plan(PlanType.STANDARD);
                    }

                    surnameCell = currentRow.getCell(1);
                    nameCell = currentRow.getCell(2);

                    for (int j=4; j<10; j++){

                        System.out.println(i + " " + j);
                        monthCell[j-4] = currentRow.getCell(j);
                        if (monthCell[j-4] != null) {
                            monthComment[j - 4] = currentRow.getCell(j).getCellComment();
                        }

                        if ((monthComment[j-4] != null) && (monthCell[j-4] !=null)){
                            String[] commentText = monthComment[j-4].getString().toString().split("\n");
                            String[] datesLine;
                            Set<Date> dateSet = new HashSet<>();
                            //System.out.println(commentText[1]);
                            if (commentText.length == 1){
                                plan.getTransactions().get(j).setStatus(TransactionState.NOTNEEDED);
                            }
                            else if (commentText[1].contains("+")){
                                datesLine = commentText[1].split("\\+");
                                for (int k=0; k< datesLine.length; k++){
                                    //System.out.println(datesLine[1]);
                                    dateSet.add(formatter.parse(datesLine[1].concat("2019")));
                                }
                            }
                            else if ((commentText[1].contains("hotovost")) || commentText[1].contains("trening") ||
                                    (commentText[1].contains("Bulla"))){
                                dateSet.add(formatter.parse("1.1.2019"));
                            }
                            else {
                                dateSet.add(formatter.parse(commentText[1].concat("2019")));
                            }
                            dates.add(dateSet);
                        }
                        if ((monthCell[j-4] != null) && (monthCell[j-4].getCellType() != CellType.BLANK)){
                            if ((monthCell[j-4].getCellType() == CellType.STRING) || (monthCell[j-4].getNumericCellValue() == 0)){
                                payments[j-4]= -1.0d;
                            }
                            else {
                                payments[j - 4] = Math.max(monthCell[j - 4].getNumericCellValue(), 0.0d);
                            }
                        }
                        else {
                            payments[j - 4] = 0.0d;
                        }
                    }

                    int notPaidMonths = 0;
                    for (int j=0; j<6; j++){
                        if (plan.getTransactions().get(j).getStatus() == TransactionState.NOTNEEDED){
                            continue;
                        }
                        if (payments[j] == -1.0d) {
                            plan.getTransactions().get(j).setStatus(TransactionState.NOTNEEDED);
                        }
                        else if (payments[j] == 0.0d) {
                            plan.getTransactions().get(j).setStatus(TransactionState.NEEDED);
                        }
                        else {
                            plan.getTransactions().get(j).setStatus(TransactionState.PAID);
                        }

                        if (plan.getTransactions().get(j).getStatus() == TransactionState.NEEDED) {
                            notPaidMonths++;
                        }
                        else {
                            if (dates.size() > 0) {
                                plan.getTransactions().get(j).setDates(dates.get(j - notPaidMonths));
                            }
                        }
                        plan.getTransactions().get(j).setPaid(payments[j]);
                    }


                    if ((category.toString().equals("U12")) || category.toString().equals("U17"))  debtCell = currentRow.getCell(15);
                    else debtCell = currentRow.getCell(16);

                    p.setSurname(surnameCell.getStringCellValue());
                    p.setName(nameCell.getStringCellValue());
                    p.setDebt(debtCell.getNumericCellValue());
                    p.setCategory(Category.valueOf(category.toString()));
                    p.setPlan(plan);

//                    transactionDAO.save(plan.getJan());
//                    transactionDAO.save(plan.getFeb());
//                    transactionDAO.save(plan.getMar());
//                    transactionDAO.save(plan.getApr());
//                    transactionDAO.save(plan.getMay());
//                    transactionDAO.save(plan.getJun());
//                    transactionDAO.save(plan.getJul());
//                    transactionDAO.save(plan.getAug());
//                    transactionDAO.save(plan.getSep());
//                    transactionDAO.save(plan.getOct());
//                    transactionDAO.save(plan.getNow());
//                    transactionDAO.save(plan.getDec());

                    System.out.println(p);
                    playerList.add(p);
                    planList.add(plan);
                }

                for (Plan plan : planList) {
                    //System.out.println(plan);
                    planDAO.save(plan);
                }

                for (Player p : playerList) {
                    playerDAO.save(p);
                }

//                    if (doneWithSheet) break;
//                    Player p = new Player();
//                    //count = 0;
//                    //ignore first row i.e culumn names
//                    currentRow = iterator.next();
//                    Iterator<Cell> cellIterator = currentRow.iterator();
//
//                    while (cellIterator.hasNext()) {
//                        p.setCategory(Category.valueOf(category.toString()));
//                        Cell currentCell = cellIterator.next();
//                        if (isCellEmpty(currentCell)) {
//                            doneWithSheet = true;
//                            break;
//                        }
//                        CellAddress cellAddress = currentCell.getAddress();
//
//                        System.out.println(cellAddress.getColumn() + " " + cellAddress.getRow());
//
//                        switch (cellAddress.getColumn()) {
//                            case 1:
//                                //System.out.println(currentCell.getStringCellValue());
//                                p.setSurname(currentCell.getStringCellValue());
//                                break;
//                            case 2:
//                                p.setName(currentCell.getStringCellValue());
//                                break;
//                            case 16:
//                                p.setDebt(currentCell.getNumericCellValue());
//                        }
//                        //count++;
//                    }
//                    playerList.add(p);
//                    System.out.println();
//
//                }
//                for (Player p : playerList) {
//                    //System.out.println(p + " " + i);
//                    playerDAO.create(p);
//                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isCellEmpty(Cell c){
        if (c == null) return true;
        if (c.getCellType() == CellType.BLANK) return true;
        return c.getCellType() == CellType.STRING && c.getStringCellValue().equals("Spolu");
    }
}


