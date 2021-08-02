package Steps;

import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//backup
//@RunWith(Cucumber.class)
//@CucumberOptions(
//		features = {"src//test//resources//Feature"},
//		glue = {"Steps", "Hooks"},
//		plugin = { "pretty"},
//		tags = {"@tag1"},
//		monochrome = true
//		)

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        plugin = {"pretty", "html:test-output", "json:target/cucumber.json"},
        features = {"src//test//resources//Feature"},
        glue = {"Steps", "Hooks"},
        tags = {"@tag1"}
)

public class RunnerTest {
}
