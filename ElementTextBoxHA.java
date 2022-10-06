package week2.day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementTextBoxHA {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//WDM gets the current version of Chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.leafground.com/input.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		//Type Name
		driver.findElement(By.xpath("(//input[contains(@class,'ui-inputfield ui-inputtext')])[1]")).sendKeys("Pranav", Keys.TAB);
		WebElement elementCity=driver.findElement(By.xpath("(//input[contains(@class,'ui-inputfield ui-inputtext')])[2]"));
		elementCity.click();
		elementCity.sendKeys(" India", Keys.TAB);

		String confirmMsgIsTextBoxEnabled;
		boolean isTxtBoxEnabled = driver.findElement(By.xpath("(//input[contains(@class,'ui-inputfield ui-inputtext')])[3]")).isEnabled();
		confirmMsgIsTextBoxEnabled=isTxtBoxEnabled? "TextBox is enabled": "TextBox is disabled";
		System.out.println(confirmMsgIsTextBoxEnabled);

		WebElement clearText = driver.findElement(By.xpath("(//input[contains(@class,'ui-inputfield ui-inputtext')])[4]"));
		clearText.click();
		clearText.clear();
		clearText.sendKeys("",Keys.TAB);

		WebElement retrieveText = driver.findElement(By.xpath("(//div[@class='grid formgrid'])[5]//input"));
		retrieveText.click();
		System.out.println(retrieveText.getDomAttribute("value"));	
		retrieveText.sendKeys("", Keys.TAB);
		
		// Confirm after entering email id, confirm if the control moved to the next element
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@placeholder='Your email and tab']")).sendKeys("Pranav@gmail.com", Keys.TAB);

		WebElement textArea=driver.findElement(By.xpath("//textarea[@placeholder='About yourself']"));
		textArea.sendKeys("Its new ma !");
		if(textArea.equals(driver.switchTo().activeElement()))
			System.out.println("Element is focused");
		else
			System.out.println("Element is not focused");

		textArea.sendKeys(Keys.TAB);

		driver.findElement(By.xpath("(//div[@class='ql-editor ql-blank']//p)[1]")).sendKeys("Its interesting");

		WebElement ageElement= driver.findElement(By.xpath("//input[contains(@id,'age')]"));
		Thread.sleep(5000); //to fix Stale Element Exception

		String ageMsg= driver.findElement(By.xpath("//span[@class='ui-message-error-detail']")).getText();
		if(ageMsg.equals("Age is mandatory")) System.out.println("Age is verified correctly!"); else System.out.println("Age is not verified");

		WebElement lblElement = driver.findElement(By.xpath("//input[@id='j_idt106:float-input']/following-sibling::label"));
		int oldX=lblElement.getLocation().getX();
		int oldY=lblElement.getLocation().getY();
		WebElement clickLbl=driver.findElement(By.id("j_idt106:float-input"));
		clickLbl.click();
		int newX=lblElement.getLocation().getX();
		int newY=lblElement.getLocation().getY();
		System.out.println("lbl moved from "+ "("+ oldX+","+oldY+")"+" to ("+newX+","+newY+")");

		//driver.close();
	}

}
