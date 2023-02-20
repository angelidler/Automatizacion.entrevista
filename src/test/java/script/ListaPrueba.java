package script;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class ListaPrueba {
 WebDriver driver;
  @BeforeClass
  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window();
		driver.get("https://demo.guru99.com/test/newtours/register.php");
  }
  
  @Test
  public void f() {
	  WebElement listado=driver.findElement(By.name("country"));
	  
	  List<WebElement> Evalualist=listado.findElements(By.tagName("option"));
	  
		
		  damelista("country", "option","AMERICAN SAMOA");
		  
		
	
	  
	//System.out.println(Evalualist.get(3).getText());
  }
  
  @AfterClass
  public void afterClass() {
	  
	  try {
		Thread.sleep(4000);
	} catch (Exception e) {
		// TODO: handle exception
	}
  }

  public void damelista(String nameList,String tagName, String selectVisible) {
	  
	  WebElement listado=driver.findElement(By.name(nameList));
	  
	  List<WebElement> Evalualist=listado.findElements(By.tagName(tagName));
	  
	  Select option= new Select(driver.findElement(By.name(nameList)));
	  
	  option.selectByVisibleText(selectVisible);
  }
}
