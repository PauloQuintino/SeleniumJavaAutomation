package Steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src//test//resources//Feature"},
		glue = {"Steps", "Hooks"},
		plugin = { "pretty"},
		tags = {"@tag1"},
		monochrome = true
		)

public class RunnerTest {
}
