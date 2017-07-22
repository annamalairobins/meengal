package com.meengal.todayscatch;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TodaysCatch {
	public WebDriver driver;
	Reporter rp = new Reporter();
	public WriteOutput wr = new WriteOutput();

	@BeforeMethod
	public void beforeTest() {
		System.out.println("Starting Test");
		System.setProperty("webdriver.chrome.driver", "C:\\Temp\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("https://www.meengal.com/");
	}

	@SuppressWarnings("static-access")
	@Test(enabled = true)
	public void tc_01()  {

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
			
			e.printStackTrace();
			Assert.fail("Exception occured");
			rp.log("Testcase failed");

		}

	}

	@SuppressWarnings("static-access")
	@Test(enabled = false)
	public void tc_02() throws InterruptedException, IOException {

		try {
			driver.findElement(By.id("linklogin")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("guestlink")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.id("pincode")).sendKeys("600073");
			new Select(driver.findElement(By.id("area"))).selectByVisibleText("Santhosa puram");
			Thread.sleep(2000);
			driver.findElement(By.id("proceed_btn")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='newinfo']/div/div/div[3]/button")).click();
			Thread.sleep(4000);
			List<WebElement> allProductsName = driver.findElements(By.xpath(".//*[@id='menu2']/ul[1]/li"));
			int size = allProductsName.size();
			size = size + 1;
			System.out.println(size);
			for (int i = 1; i < size; i++) {
				String fish2 = driver.findElement(By.xpath(".//*[@id='menu2']/ul[1]/li[" + i + "]/a[1]")).getText();
				System.out.println(fish2);
			}

		} catch (Exception e) {
			Assert.fail("Element nt fnd");
			rp.log("Element not found");

		}

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
