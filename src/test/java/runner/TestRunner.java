package test.java.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(features = {"src/test/resources/features/"}, glue = {
        "src/test/java/StepDefinition/"}, plugin = {"html:target/cucumber-html-report",
        "json:target/cucumber.json", "pretty"})

public class TestRunner {

}