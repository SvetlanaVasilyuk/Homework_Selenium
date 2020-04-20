package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = "json:target/cucumber-report",
        features = "src/test/resources/feature/sberbankFeatures",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"cucumber.StepDefinitions.Sberbank"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
