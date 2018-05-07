/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rakib
 */
public class ExcelFileRead {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        //excelFile("sss.xlsx" );

    }
    private static ArrayList<StudentMarks> storeMarks = new ArrayList<>();

    public static void excelFile(String excelPath, String unit, String sub1, String sub2, String sub3, String sub4) throws IOException, InvalidFormatException, ClassNotFoundException {
        File file = new File(excelPath);
        Workbook workbook = WorkbookFactory.create(file);

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();

        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
        }
        Sheet sheet = workbook.getSheetAt(0);
        int rowstart = sheet.getFirstRowNum();
        int rowend = sheet.getLastRowNum();
        DataFormatter dataFormatter = new DataFormatter();
        String exSub1 = null, exSub2 = null, exSub3 = null, exSub4 = null, exRoll = null;
        for (int i = rowstart + 1; i <= rowend; i++) {
            Row row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                //System.out.println(dataFormatter.formatCellValue(cell));
                switch (j) {
                    case 0:
                        exRoll = dataFormatter.formatCellValue(cell);
                        break;
                    case 1:
                        exSub1 = dataFormatter.formatCellValue(cell);
                        break;
                    case 2:
                        exSub2 = dataFormatter.formatCellValue(cell);
                        break;
                    case 3:
                        exSub3 = dataFormatter.formatCellValue(cell);
                        break;
                    default:
                        exSub4 = dataFormatter.formatCellValue(cell);
                        break;
                }
            }
            double total = 0.0;
            if (Double.parseDouble(exSub1) < Double.parseDouble(sub1) || Double.parseDouble(exSub2) < Double.parseDouble(sub2) || Double.parseDouble(exSub3) < Double.parseDouble(sub3) || Double.parseDouble(exSub4) < Double.parseDouble(sub4)) {
                total = -5.00;
            } else {
                total = Double.parseDouble(exSub1) + Double.parseDouble(exSub2) + Double.parseDouble(exSub3) + Double.parseDouble(exSub4);
            }
            storeMarks.add(new StudentMarks(exRoll, total));
        }

        workbook.close();
        for (int i = 0; i < storeMarks.size(); i++) {
            /*System.out.println(storeMarks.get(i).getRoll());
            System.out.println(storeMarks.get(i).getTotalMarks());*/
            database.store_From_excel(storeMarks.get(i).getRoll(), storeMarks.get(i).getTotalMarks());
        }
        Path p=Paths.get(excelPath);
        String un=p.getFileName().toString();
        //System.out.println();
        database.sortItem(un.charAt(0));
        storeMarks.clear();
    }

    public static void excelFile(String excelPath, String unit, String sub1, String sub2, String sub3) throws IOException, InvalidFormatException, ClassNotFoundException {
        File file = new File(excelPath);
        Workbook workbook = WorkbookFactory.create(file);

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();

        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            //System.out.println("=> " + sheet.getSheetName());
        }
        Sheet sheet = workbook.getSheetAt(0);
        int rowstart = sheet.getFirstRowNum();
        int rowend = sheet.getLastRowNum();
        DataFormatter dataFormatter = new DataFormatter();
        String exSub1 = null, exSub2 = null, exSub3 = null, exRoll = null;
        for (int i = rowstart + 1; i <= rowend; i++) {
            Row row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                //System.out.println(dataFormatter.formatCellValue(cell));
                switch (j) {
                    case 0:
                        exRoll = dataFormatter.formatCellValue(cell);
                        break;
                    case 1:
                        exSub1 = dataFormatter.formatCellValue(cell);
                        break;
                    case 2:
                        exSub2 = dataFormatter.formatCellValue(cell);
                        break;
                    default:
                        exSub3 = dataFormatter.formatCellValue(cell);
                        break;
                }
            }
            double total = 0.0;
            if (Double.parseDouble(exSub1) < Double.parseDouble(sub1) || Double.parseDouble(exSub2) < Double.parseDouble(sub2) || Double.parseDouble(exSub3) < Double.parseDouble(sub3)) {
                total = -5.00;
            } else {
                total = Double.parseDouble(exSub1) + Double.parseDouble(exSub2) + Double.parseDouble(exSub3);
            }
            storeMarks.add(new StudentMarks(exRoll, total));
            for (int j = 0; j < storeMarks.size(); j++) {
                /*System.out.println(storeMarks.get(j).getRoll());
                System.out.println(storeMarks.get(j).getTotalMarks());*/
                database.store_From_excel(storeMarks.get(j).getRoll(), storeMarks.get(j).getTotalMarks());
            }
            Path p=Paths.get(excelPath);
        String un=p.getFileName().toString();
        //System.out.println();
        database.sortItem(un.charAt(0));
            storeMarks.clear();
        }

        workbook.close();
    }
}
