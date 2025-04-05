package utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/resources/features",
            glue = "Test",
            plugin = {"pretty","html:target/cucumber-reports","json:target/cucumber-reports-json"},
            monochrome = true,
            publish = true
)
public class testRunner extends AbstractTestNGCucumberTests {

}
