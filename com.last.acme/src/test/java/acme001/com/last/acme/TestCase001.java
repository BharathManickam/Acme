package acme001.com.last.acme;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestCase001 {
	
	public ChromeDriver driver;
	
	/*1) Launch URL: https://acme-test.uipath.com/account/login
		2) Enter UserName (kumar.testleaf@gmail.com) and TAB
		3) Enter Password (leaf@12) and ENTER
		4) Mouse Over on Vendors
		5) Click Search Vendor
		6) Enter Vendor Name (Blue Lagoon)
		7) Click Search
		8) Find the Country Name based on the Vendor
		9) Click Log Out and Close browser*/
	
	@Test
	public void testcase001() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://acme-test.uipath.com/account/login");
		driver.findElementByXPath("//input[@id='email']").sendKeys("kumar.testleaf@gmail.com",Keys.TAB);
		driver.findElementByXPath("//input[@id='password']").sendKeys("leaf@12");
		driver.findElementByXPath("//button[@id='buttonLogin']").click();
		Thread.sleep(2000);
		WebElement vendor = driver.findElementByXPath("(//div[@class='dropdown']//button)[5]");
		Actions action = new Actions(driver);
		action.moveToElement(vendor).build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@href='/vendors/search']").click();
		driver.findElementByXPath("//input[@id='vendorName']").sendKeys("Blue Lagoon");
		driver.findElementByXPath("//button[@id='buttonSearch']").click();	
		WebElement table = driver.findElementByXPath("//table[@class='table']");
		List<WebElement> allrows = table.findElements(By.tagName("tr"));
		for (int i = 1; i < allrows.size(); i++) {
			List<WebElement> allcolumns = table.findElements(By.tagName("td"));
			String text = allcolumns.get(4).getText();
			System.out.println(text);
		}
		driver.findElementByLinkText("Log Out").click();
		driver.close();
		
	}

}
