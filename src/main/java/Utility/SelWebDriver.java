package Utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.SessionId;
import io.cucumber.core.api.Scenario;

public class SelWebDriver {
	public static SelWebDriver selWebDriver;
	public static WebDriver driver;
	public  SessionId session=null;
	public static Properties prop = new Properties();

	public SelWebDriver(){
    	try {
    		prop.load( new FileInputStream("./src/test/properties/application.properties") );
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static WebDriver getDriver() {
		setUpDriver();
		return driver;
	}

    public static void setUpDriver(){
        String browser = prop.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        if ("chrome".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\chandra.srivastava\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            //session = ((ChromeDriver)driver).getSessionId();
            driver = new ChromeDriver(chromeOptions);
        } else {
            throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }
    
    public void closeDriver(Scenario scenario){
            driver.close();
        }
}
