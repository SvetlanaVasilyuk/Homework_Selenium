package cucumber.stepdefinitions.bspb;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HookClass {

    @Before
    public void setConfig() throws IOException {
        File file = new File("src/test/resources/browser.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));

        Configuration.browser = properties.getProperty("browserName", "chrome");
    }
}