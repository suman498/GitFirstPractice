package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/Resources/Features",
        glue = "Test",
        plugin = {"pretty","html:target/cucumber-reports","json:target/cucumber.json"},
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
