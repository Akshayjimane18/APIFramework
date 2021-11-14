package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//features = "src/test/java/features",
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target/jsonReports/cucumber--report.json", glue = {"stepDefinations"}, features = "src/test/java/features/PaytmGetMovieAPITest.feature")
public class TestRunner {

	//,tags = {"@DeletePlace"}
}
