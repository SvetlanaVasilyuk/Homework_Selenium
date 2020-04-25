package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = "json:target/cucumber-report",
        features = "src/test/resources/feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"cucumber.stepdefinitions"},
        tags = "@all"
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
