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

            //read Headers from Excel
            List<String>headers= new ArrayList<>();
            Row headerRow=sheet.getRow(0);

            //Gets total no of Columns in header
            int totCols=headerRow.getLastCellNum();
            for(int i=0;i<totCols;i++){
                headers.add(headerRow.getCell(i).getStringCellValue());
            }


            for(int i=1;i<totCols;i++){
                Row row = sheet.getRow(i);
                if (row==null){
                    continue;
                }


                String runMode = row.getCell(2).getStringCellValue();
                if (!runMode.equals("Yes")){
                    continue;
                }

                //Mapping headers to values
                Map<String,String> rowData = new HashMap<>();
                for(int j=0;j<totCols;j++){
                    String key = headers.get(j);
                    String value = (row.getCell(j) != null) ? row.getCell(j).toString() : "";
                    rowData.put(key,value);

                }
                testData.add(new Object[]{rowData});
            }

            /*
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
            */

            wbk.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return testData.toArray(new Object[0][0]);
    }
}
