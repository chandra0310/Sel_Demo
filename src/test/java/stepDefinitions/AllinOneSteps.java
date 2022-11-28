package stepDefinitions;

import java.io.IOException;
import API.CarsDetails;
import Library.HelperService;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import PageActions.TradeMeMotorSearchPage;
import Utility.SelWebDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;

public class AllinOneSteps extends SelWebDriver {
	private String scenDesc;
    TradeMeMotorSearchPage tradeMeMotorSearchPage = new TradeMeMotorSearchPage();
    private final CarsDetails usedCarsDetails = new CarsDetails();
    SoftAssert softassert = new SoftAssert();

    @Before
	public void before(Scenario scenario) {
		this.scenDesc = scenario.getName();
	}
    
    @After
    public void after(Scenario scenario){
    	closeDriver(scenario);
    }
	
	@BeforeStep
	public void beforeStep() throws InterruptedException {
		Thread.sleep(2000);
	}

    @Given("^User navigates to tradeMe Motor Search Page$")
    public void aUserNavigatesToMotorSearchPage() throws InvalidFormatException, IOException, InterruptedException {
        tradeMeMotorSearchPage.goToMotorSearchPage();
    }

    @Then("^Number of vehicles available under Make dropdown are validated against value from property file$")
    public void captureValuesfromDropDown() {
        Integer count = TradeMeMotorSearchPage.CountValuesfromMakeDropdown();
        Integer makeCount = Integer.valueOf(prop.getProperty("makeCountUI"));
        softassert.assertEquals(count, makeCount);
        if (count == makeCount) {
            HelperService.writeToResultsFile(scenDesc + "; Total Number of named car makes from UI are: " + count + " which matches to the expected value");
        }
        else {
            HelperService.writeToResultsFile(scenDesc + "; Total Number of named car makes from UI are: " + count + " and expected number from Property file is " + makeCount);
        }

    }

    @Then("^User searches '(.+)' under make dropdown and saves the result$")
    public void aUserSearchesVehicle(String VehicleMake) throws InvalidFormatException, IOException {
        String res = TradeMeMotorSearchPage.SearchVehicle(VehicleMake);
        HelperService.writeToResultsFile(scenDesc + "; Total Number of " + VehicleMake + " named car makes are: " + res);
    }

    @Given("^I go to the Cars Category API from TradeMe sandbox endpoint$")
    public void retrieveCarDetailsfromAPI() {
        String endpointPath = "Categories/{subCategoryItem}.{file_format}";
        usedCarsDetails.setApiResponse(HelperService.getJsonResponse(prop.getProperty("apiUrl"), endpointPath,
                "UsedCars", "json"));
    }

    @When("^I get list of Cars Brands from API response$")
    public void getCarBrandfromAPIResponse() {
        usedCarsDetails.setSubcategoryList(usedCarsDetails.getApiResponse().jsonPath().getList("Subcategories"));
    }

    @Then("^Total Number of named brands of used cars available in the TradeMe UsedCars category matches to property file$")
    public void getTotalUsedCarBrands() {
        Integer count = CarsDetails.getSubcategoryList().size();
        Integer makeCount = Integer.valueOf(prop.getProperty("makeCountAPI"));
        softassert.assertEquals(count, makeCount);
        HelperService.writeToResultsFile(scenDesc + "; Total Number of named car makes from sandbox API are: " + count);
    }
}
