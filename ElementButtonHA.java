package week2.day4;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementButtonHA {
	private static boolean elementHasClass(WebElement element, String corner) {
		return Arrays.asList(element.getAttribute("class").split(" ")).contains(corner);
	}
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		//WDM gets the current version of Chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//driver.get("https://www.leafground.com/button.xhtml");
		driver.navigate().to("https://www.leafground.com/button.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String pageTitle = driver.getTitle();

		//Thread.sleep(3000);
		WebElement btnClick = driver.findElement(By.xpath("//span[text()='Click']"));
		btnClick.click();

		String navigatedPageTitle = driver.getTitle();
		if(navigatedPageTitle.equals(pageTitle)) System.out.println("Still in button page"); else System.out.println("You are moved to Dashboard");
		driver.navigate().back();

		boolean result=false;
		String confirmDisabledMsg;
		result= driver.findElement(By.xpath("//button[@id='j_idt88:j_idt92']")).isEnabled();
		//System.out.println(result);
		confirmDisabledMsg=result? "Button is enabled": "Button is disabled";
		System.out.println(confirmDisabledMsg);

		WebElement submitButton = driver.findElement(By.xpath("(//span[text()='Submit'])[1]"));
		int height=submitButton.getSize().height;
		int width=submitButton.getRect().width;
		System.out.println(String.format("Height and width is %d %d", height, width));

		String color=driver.findElement(By.xpath("//span[text()='Save']")).getCssValue("color");
		System.out.println("The color of save button is "+ color);

		WebElement eleSuccessBtn = driver.findElement(By.xpath("(//span[text()='Success'])[1]"));
		Actions actions=new Actions(driver);
		org.openqa.selenium.interactions.Action mouseHoverSuccess =actions.moveToElement(eleSuccessBtn).build();
		mouseHoverSuccess.perform();
		//Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Image']")).click();
		WebElement hiddenBtn=driver.findElement(By.xpath("//div[@class='ui-overlaypanel-content']/img"));
		hiddenBtn.click();
		//checvk if its clicked
		//		if(hiddenBtn.equals(driver.switchTo().activeElement()))
		//			System.out.println("hidden btn is clicked");
		//		else
		//			System.out.println("hidden btn is not clicked");

		String bgColor=eleSuccessBtn.getCssValue("background-color");
		if(bgColor.equals("rgb(104, 159, 56)")) System.out.println("color remains green"); System.err.println("color changed to salmon");
		////h5[text()='How many rounded buttons are there?']/following-sibling::button
		//btn class hs rounded-button
		List<WebElement> elements = driver.findElements(By.xpath("//h5[text()='How many rounded buttons are there?']/following-sibling::button"));
		int cnt=0;
		for(WebElement e : elements) {
			if(elementHasClass(e, "rounded-button")) cnt++;
		}
		System.out.println("There are " + cnt +"rounded button");
	}

}
