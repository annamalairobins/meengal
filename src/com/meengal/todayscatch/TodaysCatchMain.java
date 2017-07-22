package com.meengal.todayscatch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


public class TodaysCatchMain {
	public static WebDriver driver;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			WriteOutput wr = new WriteOutput();

			System.out.println("Starting Test");
			System.setProperty("webdriver.chrome.driver", "C:\\Temp\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.get("https://www.meengal.com/");
			
			try {
				driver.findElement(By.id("linklogin")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("guestlink")).click();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				driver.findElement(By.id("pincode")).sendKeys("600073");
				new Select(driver.findElement(By.id("area"))).selectByVisibleText("Santhosa puram");
				Thread.sleep(1000);
				driver.findElement(By.id("proceed_btn")).click();
				Thread.sleep(2000);
				boolean isPresent = driver.findElements(By.xpath(".//*[@id='newinfo']/div/div/div[3]/button")).size() > 0;
				if (isPresent == true) {
					driver.findElement(By.xpath(".//*[@id='newinfo']/div/div/div[3]/button")).click();
					Thread.sleep(1000);
				}

				boolean isPresent1 = driver.findElements(By.xpath(".//*[@id='main']/ul/li")).size() > 0;
				if (isPresent1 == true) {
					List<WebElement> fishList = driver.findElements(By.xpath(".//*[@id='main']/ul/li"));
					int size = fishList.size();
					size = size + 1;

					for (int i = 1; i < size; i++) {
						String fish = driver.findElement(By.xpath(".//*[@id='main']/ul/li[" + i + "]/div[2]")).getText();
						String fishprice = driver.findElement(By.xpath(".//*[@id='main']/ul/li[" + i + "]/div[5]/small"))
								.getText();
						wr.writeOutputTxt(fish, fishprice);
					}
				} else {
					System.out.println("No Fish available");
					wr.writeOutputTxt("No Fish Available", "Nothing");
				}

			} catch (Exception e) {
				driver.close();
				e.printStackTrace();
				

			}

		driver.close();
		new email();

	}

}
