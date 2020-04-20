package cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

@CucumberOptions(
        plugin = "json:target/cucumber-report",
        features = "src/test/resources/feature/sberbankFeatures",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"cucumber.StepDefinitions"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

//    @BeforeClass
//    public void selenideConfigure(){
//        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    }
}
