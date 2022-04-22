package Steps;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        plugin = {"pretty", "html:test-output", "json:target/cucumber.json", "timeline:test-output-thread"},
        features = {"src//test//resources//Feature"},
        glue = {"Steps", "Hooks"},
        tags = {"@CT-001"}
)

public class RunnerTest {
}
