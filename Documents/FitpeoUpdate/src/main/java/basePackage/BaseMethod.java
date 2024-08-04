package basePackage;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethod {
	
		
	public static  WebDriver driver;		
	static Actions action;
	
		public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

		public static WebDriver launchBrowserMethod(String browser) {
			try {
			switch (browser) {
			case "Chrome":		
				driver = new ChromeDriver();
				break;
			case "Edge":
				driver = new EdgeDriver();
				break;
			case "Firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("browser not installed in your system");
			}
			}catch(Exception e) {
				
			}
			threadLocalDriver.set(driver);
			return getDriver();
		}
		public static synchronized WebDriver getDriver() {
			return threadLocalDriver.get();
		}

		public static void maximizeWindowMethod() {
			try {
			driver.manage().window().maximize();
			}catch(Exception e) {}
		}

		public static void deleteAllCookiesMethod() {
			try {
			driver.manage().deleteAllCookies();
			}catch(Exception e) {}
		}

		@SuppressWarnings("deprecation")
		public static void implicitlyWaitMethod(int timeOuts){
			try {
				driver.manage().timeouts().implicitlyWait(timeOuts, TimeUnit.SECONDS);
				}catch(Exception e) {}
		}
		
		public static void staticWaitMethod(int milliSeconds) {
			try {
				Thread.sleep(milliSeconds);
			} catch (InterruptedException e) {
			}
		}
		public static String getTitleMethod() {
			try {
			return driver.getTitle();
			}catch(Exception e) {}
			
			return null;
		}
		
		public static String passwordDecodeMethod(String decodePassword) {
			try {
				return (new String(Base64.getDecoder().decode(decodePassword.getBytes())));
			} catch (Exception e) {

			}
			return null;
		}
		public static void sendKeysMethod(WebElement sendKeysElement,String values) {
			try {
			 sendKeysElement.sendKeys(values);
			 }catch(Exception e) 
			{}
		}
		public static void sendKeysMethod(WebElement sendKeysElement,Keys value ) {
			try {
			 sendKeysElement.sendKeys(value);
			 }catch(Exception e) 
			{}
		}
		
		public static String readPropertyFile(String getPropertyKey) {
			try {
				FileInputStream readFileInput = new FileInputStream(System.getProperty("user.dir")+ "\\utilities\\Fitpeo.properties");
				Properties propertyfile = new Properties();
				try {
					propertyfile.load(readFileInput);
				} catch (IOException e) {	
				}
				 return propertyfile.getProperty(getPropertyKey);
				
			} catch (FileNotFoundException e) {
				
			}
			return null;
		}
		public static String excelFileValue(String sheetName,int rowType, int cellType) throws IOException {
			File filename = new File(System.getProperty("user.dir")+ "");
			FileInputStream fis = new FileInputStream(filename);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			DataFormatter dataformat = new DataFormatter();
			XSSFSheet worksheetAt = workbook.getSheet(sheetName);
			Cell cellValue = worksheetAt.getRow(rowType).getCell(cellType);
			return dataformat.formatCellValue(cellValue);
		}
		
		public static void clickMethod(WebElement clickElement ) {			
				clickElement.click();
		}
		public static void forEachMethod(List<WebElement> listDropDown, String userValue) {
			for (WebElement selectDropDown :listDropDown ) {
				if(selectDropDown.getText().equals(userValue)) {
					selectDropDown.click();
					break;
				}
			}
		}
		public static void forEachAttributeMethod(List<WebElement> listDropDown, String userValue) {
			for (WebElement selectDropDown :listDropDown ) {
				if(selectDropDown.getAttribute("value").equals(userValue)) {
					selectDropDown.click();
					break;
				}
			}
		}
		
		public static void forMethod(List<WebElement> dropDownlist,int lastRowNumValue,String userValue) {
			for(int i=0;i<lastRowNumValue;i++) {
				if(dropDownlist.get(i).getText().equalsIgnoreCase(userValue)) {
					dropDownlist.get(i).click();
				}
			}
		}
		public static void switchWindowMethod() {
			String parentWindow = driver.getWindowHandle();
			Set<String> totalwindowhandles = driver.getWindowHandles();
			Iterator<String> iterator = totalwindowhandles.iterator();
			while(iterator.hasNext()) {
				String childWindow =iterator.next();
				if(!parentWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
				}
			}
		}
		public static void clearMethod(WebElement clearElement) {
			try {
				clearElement.clear();
			}catch(Exception e) {}
			
		}
		public static void browserCloseMethod() {
			try {
			driver.close();
			}catch(Exception e) {
				
			}
		}

		public static void browserQuitMethod() {
			try {
			driver.quit();
			}catch(Exception e) {}
		}

		public static void navigationMethod(String browserUrl) {
			try {
			driver.navigate().to(browserUrl);
			}catch(Exception e) {
				
			}
		}

		public static void navigationMethod(char navigate) {
			try {
			switch (navigate) {
			case 'F':
				driver.navigate().forward();
				break;
			case 'B':
				driver.navigate().back();
				break;
			default:
				driver.navigate().refresh();
			}
			}catch(Exception e) {}
		}

		public static void getBrowserMethod(String browserUrl) {
			try {
			driver.get(browserUrl);
			}catch(Exception e) {
			}
		}

		public static void alertHandlingMethod(boolean confirmation) {
			try {
			Alert alertMessage = driver.switchTo().alert();
			if (confirmation) {
				alertMessage.accept();
			} else {
				alertMessage.dismiss();
			}
			}catch(Exception e) {}
		}

		public static void alertHandlingMethod(String alertSendKeys) {
			try {
			driver.switchTo().alert().sendKeys(alertSendKeys);
			}catch(Exception e) {}
		}

		public static String alertHandlingMethod() {
			try {
			return driver.switchTo().alert().getText();
			}catch(Exception e) {}
			return null;
		}
		public static boolean isEnabledMethod(WebElement isEnableWebElement) {
			 return isEnableWebElement.isEnabled();	
		}
		public static boolean isdisplayedMethod(WebElement isDisplayWebElement) {
			
			return isDisplayWebElement.isDisplayed();
			
		}
		
		public static boolean containsMethod(String acutal,String expected) {
			return acutal.contains(expected);
		}
		
		public static int listSizeMethod(List<WebElement> element) {
			return element.size();
		}
		public static String getTextMethod(WebElement textElement) {
			return textElement.getText();
		}
		public static boolean isSelectedMethod(WebElement isSelectWebElement) {	
			return isSelectWebElement.isSelected();
		}
		public static void submitMethod(WebElement element) {
			element.submit();
		}
		public static void takesScreenShotMethod(String name) throws IOException {
			try {
		File originScreenShotFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(originScreenShotFile, new File(System.getProperty("user.dir") +"//ScreenShots//ScreenShot_"+name+".png"));
		}catch(Exception e) {}
		}

		public static WebDriverWait explicitWaitMethod(Duration timeUnits){
			try {
			WebDriverWait wait = new WebDriverWait(driver,timeUnits);
			return wait;
			}catch(Exception e) {}
			return null;
		}
		public static void fluentWaitMethod(Duration timeouts,Duration pollingTimeOuts) {
			try {
			Wait wait = new FluentWait(driver).withTimeout(timeouts).pollingEvery(pollingTimeOuts).ignoring(Exception.class);
			}catch(Exception e) {}
		}
		public static void excuteScriptWindowSrollDownMethod(int horizontal,int vertical) {
			JavascriptExecutor scriptScrollDown=(JavascriptExecutor) driver;
			scriptScrollDown.executeScript("window.scrollBy("+horizontal +","+vertical+")", "");
		}
		
		public static void excuteScriptSetAttributeMethod(WebElement element,String attributValue,String attributeType) {
			JavascriptExecutor setAttributeValue = (JavascriptExecutor) driver;
			setAttributeValue.executeScript("arguments[0].setAttribute('"+attributeType+"', '"+attributValue+"')", element);
		}
		public static void excuteScriptUpdateAttributeMethod(WebElement element,String attributeName, String newValue) {
			JavascriptExecutor attributeValue = (JavascriptExecutor) driver;
			attributeValue.executeScript("arguments[0].removeAttribute(arguments[1]);", element, attributeName);
			attributeValue.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, newValue);
		}
		
		public static void excuteScriptClickMethod(WebElement element) {
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].click();", element);
		}
		
		public static void executeScriptScrollToElement(WebElement element) {
			JavascriptExecutor scriptScrollTo = (JavascriptExecutor) driver;
			scriptScrollTo.executeScript("arguments[0].scrollIntoView()", element);
		}
		
		public static String getAttributeMethod(WebElement element, String attributeType) {
			return element.getAttribute(attributeType);
		}
		
		public static int countMultipleElements(List<WebElement> multipleElement) {
			return multipleElement.size();
			}
		
		public static int parseIntMethod(String value) {
			return Integer.parseInt(value);
		}
		
		public static void dragAndDropMethod(WebElement element, int target) {
			action = new Actions(driver);
			action.dragAndDropBy(element, target, 0).perform();
		}
		
		public static void clickAndHoldMethod(WebElement element,int targetValue,int currentValue,int maxValue,int minValue,int width ) {
			action = new Actions(driver);
			action.clickAndHold(element).moveByOffset(calculateOffsetMethod(element,targetValue, currentValue,maxValue,minValue,width), 0).release().perform();		
		}
		public static int calculateOffsetMethod(WebElement element ,int  targetValue,int currentValue,int maxValue,int minValue,int sliderWidth) {
	        int totalRange = maxValue - minValue;
	        int valueRange = (targetValue - currentValue)+maxValue;
	        int proportion = valueRange / totalRange;
	        return (sliderWidth * proportion);
		}
		
		public static void robotMethod() throws AWTException {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_A);
			r.keyPress(KeyEvent.VK_DELETE);
			r.keyRelease(KeyEvent.VK_DELETE);
		}

}
