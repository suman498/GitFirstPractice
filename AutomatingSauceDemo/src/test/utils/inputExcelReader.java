package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inputExcelReader {
    private Sheet sheet;

    public inputExcelReader(String filePath, String sheetName){

        try{
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            sheet=workbook.getSheet(sheetName);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load input Excel File");
        }

    }

    public Map<String, String> getRowData(int rowNum){
        Map<String,String> rowData = new HashMap<>();
        Row headerRow=sheet.getRow(0);
        Row row=sheet.getRow(rowNum);

        for (int i=0;i<headerRow.getLastCellNum();i++){

            //fetching the column name
            String columnName=headerRow.getCell(i).getStringCellValue();
            //fetching the value
            Cell cell = row.getCell(i);
            String Value="";

            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING -> Value = cell.getStringCellValue();
                    case NUMERIC -> Value = String.valueOf((long) cell.getNumericCellValue());
                    case BOOLEAN -> Value = String.valueOf(cell.getBooleanCellValue());
                    case FORMULA -> Value = cell.getCellFormula();
                    case BLANK -> Value = "";
                    default -> Value = "";
                }
            }

            //adding data in hashMap , columnName and corresponding value
            rowData.put(columnName,Value);
            
        }

        return rowData;
    }

    //Return all columns exclusing header
    //for testNG data provider
    public List<Map<String,String>> getAllData(){
        List<Map<String,String>> data = new ArrayList<>();
        for(int i=1;i<=sheet.getLastRowNum();i++){
            data.add(getRowData(i));
        }
        return data;

    }

}
