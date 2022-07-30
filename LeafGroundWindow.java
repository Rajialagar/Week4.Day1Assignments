package week4.day1Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundWindow {

	public static void main(String[] args) {

		//http://www.leafground.com/pages/Window.html

		//Launching Browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();

		//Get primary windowHandle ID
		String window = driver.getWindowHandle();
		System.out.println(window);
		driver.findElement(By.id("home")).click();
				
		//Get all windowHandle IDs in Set and change to List
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allWindowsList = new ArrayList<String>(allWindows);
		String subWindow = allWindowsList.get(1);
		System.out.println(subWindow);
		
		//Find number of opened windows
		System.out.println("The number of windows opened: " + allWindowsList.size());
		
		//Switch the control to sub window and close
		driver.switchTo().window(subWindow);
		driver.close();
		
		//Switch back to primary window
		driver.switchTo().window(window);
		
		
		// explicit wait - to wait for the 2 new windowss to be opened
		driver.findElement(By.id("color")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		if (wait.until(ExpectedConditions.numberOfWindowsToBe(3))) {
			System.out.println("Wait option successful");
		}
	
		
	}

}
