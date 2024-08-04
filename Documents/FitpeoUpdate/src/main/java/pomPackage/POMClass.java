package pomPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMClass {
	
	public static WebDriver driver;
	private String revenueValue;
	private String dynamicRPM;
	
	public POMClass(WebDriver driver) {		
			this.driver=driver;
			PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath="//div[text()='Revenue Calculator']/..")
	private WebElement RevenueCalculatorTab;

	public WebElement getRevenueCalculatorTab() {
		return RevenueCalculatorTab;
	}
	
	@FindBy(xpath="//h4[contains(@class,'12siehf')]")
	private WebElement medicareEligiblePatientText;

	public WebElement getMedicareEligiblePatientText() {
		return medicareEligiblePatientText;
	}
	
	@FindBy(xpath="//span[contains(text(),'Patients')]")
	private WebElement patientsText;
	
	public WebElement getPatientsText() {
		return patientsText;
	}
	
	public void setRevenueValue(String value) {
        this.revenueValue = value;
    }
    public WebElement getRevenueValue() {
        return driver.findElement(By.xpath("//input[contains(@class,'1o6z5ng')and @value='"+revenueValue+"']"));
    }	
 
    public void setDynamicRPM(String value) {
    	this.dynamicRPM=value;	
    }
    public WebElement getDynamicRPM() {
    	return driver.findElement(By.xpath("//p[text()='"+dynamicRPM+"']/..//input"));
    }
    
    @FindBy(xpath="//span[contains(@class,'sy3s50')]")
    private WebElement slider;
    
    public WebElement getSlider() {return slider;}
    
    @FindBy(xpath="//span[contains(@class,'sy3s50')]/input")
    private WebElement RevenueCalculatorSlider;
    
    
    public WebElement getRevenueCalculatorSlider() {
		return RevenueCalculatorSlider;
	}

	@FindBy(xpath="//input[contains(@class,'MuiInputBase')]")
    private WebElement RevenueCalculatorTextBox;

	public WebElement getRevenueCalculatorTextBox() {
		return RevenueCalculatorTextBox;
	}
	
	@FindBy(xpath="//p[contains(text(),'Total Recurring Reimbursement')]/p")
	private WebElement totalReimbursementText;

	public WebElement getTotalReimbursementText() {
		return totalReimbursementText;
	}

}
