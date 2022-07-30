package week4.day1Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceCustomerService {

	public static void main(String[] args) throws InterruptedException {

		//Launching Browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();

		//Login by using the given username and pwd
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(2000);
		//Get parent windowHandle ID
		String window = driver.getWindowHandle();

		//Click learn more options from Mobile publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//Get all new tab IDs in Set and change to List
		Set<String> allTabs = driver.getWindowHandles();
		List<String> allTabList = new ArrayList<String>(allTabs);

		//Get windowHandle ID for the sub window
		String subTab = allTabList.get(1);

		//Switch to the the newTab opened
		driver.switchTo().window(subTab);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();


		//Get all new tab IDs in Set and change to List
		Set<String> allSubTabs = driver.getWindowHandles();
		List<String> allTabList1 = new ArrayList<String>(allSubTabs);
	//	System.out.println(allTabList1.size());

		//Get windowHandle ID for the sub window
		String subTab1 = allTabList1.get(1);

		//Switch to the the newTab opened and get the title
		driver.switchTo().window(subTab1);
		System.out.println(driver.getTitle());
		
		//Switch to the parent window
		driver.switchTo().window(window);
		System.out.println(driver.getTitle()); // confirmation of Parent window by getting title of the window
		
		//Close the browser
		driver.close();
		

	}

}
