package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = "json:target/cucumber-report",
        features = "src/test/resources/feature/ru.auto",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"cucumber.stepdefinitions.ru.auto"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
