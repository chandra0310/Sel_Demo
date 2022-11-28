package PageActions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import Utility.SelWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static Utility.SelWebDriver.*;

public class TradeMeMotorSearchPage {

	public TradeMeMotorSearchPage(){
	}

	public void goToMotorSearchPage() throws InvalidFormatException, IOException, InterruptedException {
		String URL= prop.getProperty("URL");
		WebDriver driver=SelWebDriver.getDriver();
        driver.get(URL);
		Thread.sleep(5000);
    }

	public static Integer CountValuesfromMakeDropdown(){
		Integer optionsCount = driver.findElement(By.name ("selectedMake")).getText().split("\n").length;
		return optionsCount;

	}

	public static String SearchVehicle(String vehicleMake) {
		driver.findElement(By.name ("selectedMake")).sendKeys(vehicleMake);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		String result = driver.findElement(By.xpath("(//*[normalize-space(text()) and normalize-space(.)='(optional)'])[3]/preceding::h3[1]")).getText();
		String[] arrayRes = result.split(" ");
		String vehicleSearchCount = arrayRes[arrayRes.length-2];
		return vehicleSearchCount;
	}

}