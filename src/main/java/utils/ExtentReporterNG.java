package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports extent;
	 public static ExtentReports getReportObjects() {
		 if(extent ==null)
		 {
	    	String path= System.getProperty("user.dir") + "\\reports\\ExtentReport.html";
	    	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
	        sparkReporter.config().setReportName("Functional Test Automation Report");
	        sparkReporter.config().setDocumentTitle("Selenium Test Report");
	        
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Tester", "Anupama Deshmukh");
	        extent.setSystemInfo("Environment", "QA");
	    }
		 return extent;
	 }
}
