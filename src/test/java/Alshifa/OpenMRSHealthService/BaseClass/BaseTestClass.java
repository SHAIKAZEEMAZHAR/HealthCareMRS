package Alshifa.OpenMRSHealthService.BaseClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Alshifa.OpenMRSHealthService.Utility.ConfigFile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestClass {
	
	protected WebDriver driver;
	private ExtentReports extentReports;
	private ExtentSparkReporter extentSparkReporter;
	private ExtentTest extentTest;
	

	public enum browserType {
		CHROME, EDGE
	}

	private String url = ConfigFile.getProperty("app.url");

	public void setUpBrowser(browserType browserType) {
		
		
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = "extent-spark-report_" + timestamp + ".html";  // Report file name with timestamp
        
        // Step 2: Specify the folder to store the report
        String reportFolderPath = "./results/";  // Folder to store reports
        
        // Step 3: Ensure that the results folder exists, create if not
        File reportFolder = new File(reportFolderPath);
        if (!reportFolder.exists()) {
            boolean created = reportFolder.mkdirs();  // Create the folder if it doesn't exist
            if (created) {
                System.out.println("Results folder created: " + reportFolderPath);
            } else {
                System.out.println("Failed to create results folder: " + reportFolderPath);
            }
        }
        
        // Step 4: Create the complete report file path with timestamp
        String reportFilePath = reportFolderPath + reportFileName;
		
		extentSparkReporter = new ExtentSparkReporter(reportFilePath); // Report will be saved here
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        
        extentTest = extentReports.createTest("Functional Test Cases");

		switch (browserType) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Unexpected browser type: " + browserType);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if (url != null && !url.isEmpty()) {
			driver.get(url);
		} else {
			throw new IllegalStateException("URL not found in config file.");
		}
	}
	
	public ExtentTest getExtentTest() {
        return extentTest;
    }

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		
		if (extentReports != null) {
            extentReports.flush(); // Flushes the report at the end of the test
        }
	}
}
