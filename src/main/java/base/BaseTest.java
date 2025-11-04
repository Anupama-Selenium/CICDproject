package base;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReporterNG;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
public class BaseTest {
    public static ExtentReports extent;
    public static ExtentSparkReporter sparkReporter;
//    public static WebDriver driver;
    private static ThreadLocal<ExtentTest> logger = new ThreadLocal<>();
//    public ExtentTest logger;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getDriver()
    {
    	return driver.get();
    }
    public static ExtentTest getLogger() {
        return logger.get();
    }
    @BeforeSuite
    public void setupExtent()
    {
    	extent = ExtentReporterNG.getReportObjects();
    }
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser, Method testMethod) {
    	ExtentTest test = extent.createTest(testMethod.getName());
    	logger.set(test);
    	WebDriver localDriver = setupDriver(browser);
        driver.set(localDriver);
        
        getDriver().manage().window().maximize();
        getDriver().get(utils.Constants.url);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
        	getLogger().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
        	getLogger().skip(result.getThrowable());
        } else {
        	getLogger().pass("Test passed");
        }
        getDriver().quit();
        driver.remove();
        logger.remove();
    }
    @AfterSuite
    public void tearDown()
    {
    	extent.flush();
    }
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		File dstFile = new File(System.getProperty("user.dir")+"//screenshots//"+testCaseName+".png");
		FileUtils.copyFile(srcFile, dstFile);
		return dstFile.getAbsolutePath();
		
		
	}
    
//    public WebDriver setupDriver(String browser) {
//        if (browser.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            return new ChromeDriver();
//        } else if (browser.equalsIgnoreCase("firefox")) {
//            WebDriverManager.firefoxdriver().setup();
//            return new FirefoxDriver();
//        } else if (browser.equalsIgnoreCase("edge")) {
//            WebDriverManager.edgedriver().setup();
//            return new EdgeDriver();
//        } else {
//            throw new IllegalArgumentException("Invalid browser: " + browser);
//        }
//    }
    public WebDriver setupDriver(String browser) {
        System.out.println("Launching browser: " + browser);
        WebDriver driver = null;
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Invalid browser: " + browser);
            }
            System.out.println(browser + " launched successfully!");
        } catch (Exception e) {
            System.out.println("❌ Failed to launch " + browser + ": " + e.getMessage());
            e.printStackTrace();
        }
        return driver;
    }
    
    
}