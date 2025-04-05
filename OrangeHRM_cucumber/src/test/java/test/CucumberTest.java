package test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/resources/features",
            glue = "test",
            plugin = {"pretty","html:target/cucumber-reports.html","json:target/cucumber-reports-json"},
            monochrome = true,
            publish = true
)
public class CucumberTest extends AbstractTestNGCucumberTests {

}
