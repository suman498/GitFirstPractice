package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {

    public static Object[][] getRunnableTests(){
        List<Object[]> testData= new ArrayList<>();
        try{
            FileInputStream excl = new FileInputStream("testData/inputExcel.xlsx");
            Workbook wbk = WorkbookFactory.create(excl);
            Sheet sheet = wbk.getSheet("Sheet1");

            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                String testCaseID = row.getCell(0).getStringCellValue();
                String testCaseName = row.getCell(1).getStringCellValue();
                String runMode = row.getCell(2).getStringCellValue();

                if(runMode.equals("Yes")){
                    //adding in arrayList
                    testData.add(new Object[]{testCaseID,testCaseName});
                }
            }

            wbk.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return testData.toArray(new Object[0][0]);
    }
}
