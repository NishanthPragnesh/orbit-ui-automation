package test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources",
    glue = {"test", "hooks"}, // ← Add "hooks" here
    plugin = {"pretty", "html:target/cucumber-reports/cucumber-html-report.html", "json:target/cucumber-reports/CucumberTestReport.json"},
    monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {}
