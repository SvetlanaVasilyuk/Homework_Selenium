package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = "json:target/cucumber-report",
        features = "src/test/resources/feature/autoruFeatures",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"cucumber.StepDefinitions.Autoru"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
