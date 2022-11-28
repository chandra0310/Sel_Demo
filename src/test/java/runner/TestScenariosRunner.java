package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions( tags = {"@tradeMe_Demo"},glue = {"stepDefinitions"}, plugin = {"html:target/cucumber-reports/HomePage/cucumber-pretty","json:target/json-cucumber-reports/homepage/cukejson.json",
		"testng:target/testng-cucumber-reports/HomePage/cuketestng.xml" }, features = {"src/test/features"})
public class TestScenariosRunner extends AbstractTestNGCucumberParallelTests {
	
	@BeforeClass
	public static void before() {
		System.out.println("Before - "+System.currentTimeMillis());
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After - "+System.currentTimeMillis());
	}

}
