package practiceone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 測試情境 
 * 1.瀏覽Google 網站
 * 2.輸入 Selenium 關鍵字搜尋 
 * 3.印出目前視窗的Title
 */

public class TestNGLearn11 {
	private WebDriver driver;
	private String baseURL = "http://www.google.com/";
	private String browserClass = "";

	@Parameters({ "browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
				browserClass = browser;
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Chrome")) {
				browserClass = browser;
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void visit_google_getTitle() {
		driver.navigate().to(baseURL);
		System.out.println("The Existing Window title: (" + browserClass + ")" + driver.getTitle());
	}

	@Test(dependsOnMethods = { "visit_google_getTitle" })
	public void search_TestCase() throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys("Selenium");
		driver.findElement(By.name("q")).submit();
		Thread.sleep(5000);
		System.out.println("The Window title of the Search: (" + browserClass + ")" + driver.getTitle());
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
