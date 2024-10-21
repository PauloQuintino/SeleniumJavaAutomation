package Steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        plugin = {"pretty"},
        features = {"src//test//resources//Feature"},
        glue = {"Steps", "Hooks"},
        tags = "@CT-001"
)

public class RunnerTest {
}
