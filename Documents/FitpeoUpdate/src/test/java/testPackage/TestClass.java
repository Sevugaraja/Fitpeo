package testPackage;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basePackage.BaseMethod;
import pomPackage.POMClass;

@Listeners(basePackage.ListenersInterface.class)
public class TestClass extends BaseMethod {

	public static WebDriver driver;
	public static POMClass pageObjectClass;
	public static SoftAssert assertSoft = new SoftAssert();
	public static Logger log;

	@BeforeSuite
	public static Logger logger() {
		log = Logger.getLogger("FitPeo");
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.DEBUG);
		return log;
	}

	@Test(priority = 1)
	public static void TestCase1() throws IOException {
		driver = launchBrowserMethod(readPropertyFile("Browser"));
		pageObjectClass = new POMClass(driver);
		staticWaitMethod(2000);
		log.info("************ Launch " + readPropertyFile("Browser") + " Browser*********");
		maximizeWindowMethod();
		deleteAllCookiesMethod();
		staticWaitMethod(2000);
		getBrowserMethod(readPropertyFile("URLFitPeo"));
		staticWaitMethod(2000);
		takesScreenShotMethod("Home_Page");
		assertSoft.assertEquals(driver.getTitle(), readPropertyFile("ExpectedHomePageTitle"));
		log.info("************ FitPeo Application launch*********");
	}

	@Test(priority = 2)
	public static void TestCase2() throws IOException {
		clickMethod(pageObjectClass.getRevenueCalculatorTab());
		staticWaitMethod(4000);
		takesScreenShotMethod("Revenue_Calculator");
		log.info("************ Navigate to Revenue calcutor Tab *********");
		assertSoft.assertTrue(containsMethod(driver.getCurrentUrl(), readPropertyFile("RevenueCalculatorText")));
	}

	@Test(priority=3)
	public static void TestCase3() throws Exception {
		executeScriptScrollToElement(pageObjectClass.getMedicareEligiblePatientText());
		staticWaitMethod(3000);
		takesScreenShotMethod("Revenue_Calculator_Slider_Value");
		clickAndHoldMethod(pageObjectClass.getSlider(), parseIntMethod(readPropertyFile("RevenueValue")),0, parseIntMethod(readPropertyFile("RevenueMaximumValue")),parseIntMethod(readPropertyFile("RevenueWidth")), parseIntMethod(readPropertyFile("RevenueWidth")));
		staticWaitMethod(3000);
		assertSoft.assertEquals(getAttributeMethod(pageObjectClass.getRevenueCalculatorTextBox(), "value"), readPropertyFile("RevenueValue"));
			
	}

	@Test(priority = 4)
	public static void TestCase4() throws Exception {
		executeScriptScrollToElement(pageObjectClass.getMedicareEligiblePatientText());
		staticWaitMethod(3000);
		clickMethod(pageObjectClass.getRevenueCalculatorTextBox());
		robotMethod();
		staticWaitMethod(3000);
		sendKeysMethod(pageObjectClass.getRevenueCalculatorTextBox(), readPropertyFile("RevenueTextFieldUpdateValue"));
		log.info("************ update New Patients successfully *********");
		assertSoft.assertEquals(getAttributeMethod(pageObjectClass.getRevenueCalculatorSlider(), "value"),
				readPropertyFile("RevenueTextFieldUpdateValue"));
		log.info("************ slider value and Patients Text field are same *********");

	}

		@Test(priority=5)
	public static void TestCase5() throws Exception {
		staticWaitMethod(3000);
		pageObjectClass.setDynamicRPM(readPropertyFile("RPM1"));
		clickMethod(pageObjectClass.getDynamicRPM());
		staticWaitMethod(3000);
		pageObjectClass.setDynamicRPM(readPropertyFile("RPM2"));
		clickMethod(pageObjectClass.getDynamicRPM());
		staticWaitMethod(3000);
		pageObjectClass.setDynamicRPM(readPropertyFile("RPM3"));
		clickMethod(pageObjectClass.getDynamicRPM());
		staticWaitMethod(3000);
		pageObjectClass.setDynamicRPM(readPropertyFile("RPM4"));
		clickMethod(pageObjectClass.getDynamicRPM());
		staticWaitMethod(3000);
	}

		@Test(priority=6)
	public static void TestCase6() throws Exception {
		staticWaitMethod(3000);
		System.out.println("Total Recurring Reimbursement for all Patients Per Month : "
				+ getTextMethod(pageObjectClass.getTotalReimbursementText()));
		takesScreenShotMethod("Total_Reimbursement");
		log.info("************ Total Recurring Reimbursement for all Patients Per Month Print successfully *********");
		assertSoft.assertAll();
	}

		@AfterSuite
	public static void teardownMethod() {
		browserQuitMethod();
	}

}
