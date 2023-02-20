package script;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Login {

	WebDriver driver;
	String enter = "100";// Ingreso de cantidad a transferir

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window();
		driver.get("https://demo.testfire.net/login.jsp");

	}

	@Test(priority = 1, description = "Test de Inicio de sesion")
	public void singIn() {
		// User login
		WebElement user = driver.findElement(By.id("uid"));
		WebElement password = driver.findElement(By.id("passw"));
		WebElement buttonLogin = driver.findElement(By.name("btnSubmit"));

		waitTime(1000);
		user.sendKeys("jsmith");
		// pass user
		password.sendKeys("Demo1234");
		// button login
		buttonLogin.submit();

	}

	@Test(priority = 2, description = "Test de ingresos  a transfer")
	public void Transfers() {

		// Seleccion de opcion de transferencia
		WebElement urlTransfer = driver.findElement(By.id("MenuHyperLink3"));
		waitTime(1000);
		urlTransfer.click();
		waitTime(1000);

	}

	@Test(priority = 3, description = "Test de cuenta, cantidad a transferir y transferencia")
	public void creditTransfer() {

		// Seleccion de lista tarje de cridito

		damelista("toAccount", "option", 2);
		
		// Ingreso de monto y boton de transferencia
		WebElement amount = driver.findElement(By.id("transferAmount"));
		WebElement buttonTransfer = driver.findElement(By.id("transfer"));
		amount.sendKeys(enter);

		waitTime(1000);

		buttonTransfer.click();

	}

	@Test(priority = 4, description = "En este test evaluamos si los resultados son consistentes")
	public void evaluateTransfers() {

		WebElement Message = driver.findElement(By.xpath("//*[@id=\"_ctl0__ctl0_Content_Main_postResp\"]/span"));

		Evalua(getTransfer(Message.getText()), enter);

		waitTime(1000);

	}

	@AfterClass
	public void afterClass() {
		//
		WebElement signOff = driver.findElement(By.xpath("//*[@id=\"LoginLink\"]/font"));
		signOff.click();
		waitTime(2000);
		driver.close();

	}

//Metodos para obtener el monto de dinero transferido
	public String getTransfer(String transfer) {
		int i;
		for (i = 0; i < transfer.length(); i++) {

			if (transfer.charAt(i) == ' ') {
				break;
			}
		}
		return transfer.subSequence(0, i).toString();
	}

// Metodo evalua si los resultados de cantidad transferidad son consistentes
	public void Evalua(String ValueA, String ValueB) {

		double a = Double.parseDouble(ValueA);
		double b = Double.parseDouble(ValueB);

		assertEquals(a, b);

	}

// Metodo de tiempos de esperas 
	public static void waitTime(int time) {

		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("only whole numbers are allowed");

		}
	}

// Metodo para interar una lista de un DropdownList por un index de opcion que recibe en numeros entenros
	public void damelista(String nameList, String tagName, int SelectOption) {
		
		WebElement listado = driver.findElement(By.name(nameList));
		
		List<WebElement> Evalualist = listado.findElements(By.tagName(tagName));
		
		if(Evalualist.size()-1<SelectOption) {
		
			JavascriptExecutor js =  (JavascriptExecutor) driver;
			js.executeScript("alert ('Error: La index de lista ingresado es mayor que la lista existente')");
			System.out.println("Error: La index de lista ingresado es mayor que la lista existente");
			Alert alerta= driver.switchTo().alert();
			waitTime(1000);
			alerta.accept();
			driver.close();
		}
		Select option = new Select(driver.findElement(By.name(nameList)));

		option.selectByVisibleText(Evalualist.get(SelectOption).getText());
	}
// Metodo para interar una lista de un DropdownList toma el nombre de la lista selecionada
	public void damelista(String nameList, String tagName, String selectVisible) {

		WebElement listado = driver.findElement(By.name(nameList));

		List<WebElement> Evalualist = listado.findElements(By.tagName(tagName));

		Select option = new Select(driver.findElement(By.name(nameList)));

		option.selectByVisibleText(selectVisible);
	}
}
