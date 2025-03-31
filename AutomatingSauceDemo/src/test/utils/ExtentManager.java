package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance(){
        if(extent==null){
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            String date = formatter.format(new Date());
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/FullCheckoutTestReport"+date+".html");
            extent=new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest createtest(String testName){
        test=getInstance().createTest(testName);
        return test;

    }

    public static void flushReports(){
        if(extent!=null){
            extent.flush();
        }
    }

}
