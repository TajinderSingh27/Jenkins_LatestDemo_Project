package loginTC;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Test {
	private ExtentReports extent=new ExtentReports();
	private ExtentSparkReporter spark =new ExtentSparkReporter("extent.html");
	private WebDriver driver;	
	
	@BeforeSuite
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	extent.attachReporter(spark);
	System.out.println(driver);
	
	}

	@Test
	public void login() throws InterruptedException, IOException {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		ExtentTest test=extent.createTest("*****Login Functionality*****").createNode("first").assignAuthor("Tajinder Singh").assignCategory("Regression").assignDevice("Windows");
		
		driver.get("http://opensource-demo.orangehrmlive.com/"); 
		Thread.sleep(15000);	
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin" ) ; 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys( "admin123") ; 
		driver.findElement(By.xpath("//button[@type='submit']")).click() ; 
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle() , "OrangeHRM");
		System.out.println("Test scenario got passed successfully.");
		test.log(Status.PASS, "Login functioality verified");
		test.pass("Passed");
		driver.close(); 	
	}
	
	@AfterSuite
	public void tearDown() {
	extent.flush();
	if(driver!=null)
	driver.quit();
	}
}
