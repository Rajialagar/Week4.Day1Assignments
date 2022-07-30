package week4.day1Assignments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContacts {

	public static void main(String[] args) throws InterruptedException {

		//Launching Browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();

		//Login to leaftaps website
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();

		//Go to Contact tab
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		//Get primary windowHandle ID
		String window1 = driver.getWindowHandle();

		//Click on Merge contacts
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//td[@class='titleCell']/following::a[1]")).click();

		//Get all windowHandle IDs in Set and change to List
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allWindowsList = new ArrayList<String>(allWindows);

		//Get windowHandle ID for the sub window
		String subWindow = allWindowsList.get(1);
		
		//Switch the control to sub window and click the first result
		driver.switchTo().window(subWindow);
//		System.out.println(subWindow);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a")).click();

		//Switch back to primary window
		driver.switchTo().window(window1);
		driver.findElement(By.xpath("//td[@class='titleCell']/following::a[2]")).click();

		//Switch the control to sub window and click the second result
		Set<String> allWindows1 = driver.getWindowHandles();
		List<String> allWindowsList1 = new ArrayList<String>(allWindows1);
		String subWindow1 = allWindowsList1.get(1);
		driver.switchTo().window(subWindow1);
//		System.out.println(subWindow1);
		driver.findElement(By.xpath("((//table[@class='x-grid3-row-table'])//tr)[3]//a")).click();
		
		//Switch back to primary window
		driver.switchTo().window(window1);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		
		//Switch to Aleret
		Alert alert = driver.switchTo().alert();
		
		//Accept the alert
		alert.accept();
		
		Thread.sleep(2000);
		System.out.println(driver.getTitle()); 
		

	}

}
