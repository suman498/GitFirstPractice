package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/features"
		,glue="stepDefinitions"
		,plugin= {"pretty","json:target/jsonReports/cucumber-report.json"}
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}
