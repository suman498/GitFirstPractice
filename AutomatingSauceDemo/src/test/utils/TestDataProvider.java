package utils;

import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class TestDataProvider {

    @DataProvider(name="testData")
    public Object[][] getTestdata(){
        //loading InputExcel Data
        inputExcelReader excel = new inputExcelReader(configReader.getProperty("inputExcelPath"),configReader.getProperty("sheetName"));
        List<Map<String,String>> testData=excel.getAllData();

        //Convert List<Map<String,String>> to Object[][]
        Object[][] inputData =new Object[testData.size()][1];

        for(int i=0;i<testData.size();i++){
            inputData[i][0]= testData.get(i);
            //System.out.println(inputData[i][0]);

        }
        return inputData;

    }

}
