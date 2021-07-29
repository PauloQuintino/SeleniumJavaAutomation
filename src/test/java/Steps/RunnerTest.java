package Steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src//test//resources//feature"},
		glue = {"Steps", "Hooks"},
		plugin = { "pretty", "html:test-output", "json:target/cucumber.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags = {"@tag1"},
		monochrome = true
		)

public class RunnerTest {
}
