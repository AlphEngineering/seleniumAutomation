package setup;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        //path of feature file
        features = "src/test/features",
        //path of step definition file
        glue = "stepdefinitions",
        monochrome = true,
        //path of reports
        plugin = {"pretty", "html:resources/reports"}
        )
public class TestRunner extends AbstractTestNGCucumberTests {

}
