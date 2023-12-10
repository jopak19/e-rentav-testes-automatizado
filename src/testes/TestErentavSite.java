package testes;



import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestErentavSite {
	
	/* 
	 * Classe de Testes automatizados para site E-rentav
	 * 
	 * Configurar path do driver de navegador de acordo com a máquina 
	 * Adicionar libs do Selenium web driver
	 * 
	 * */
	
	private WebDriver driver;
	private String baseUrl;
	
	//Caminho para driver do Google chrome
	private String driverPath = "/home/joao/eclipse-workspace/selenium/geckodriver";
	
	@Before
	public void setUp() throws Exception {
		//System.setProperty("webdriver.firefox.driver", driverPath);
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "https://dev.e-rentav.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Faz login no sistema
		driver.get(baseUrl);
		driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/input")).sendKeys("joao.siqueira@ufrn.br");
		driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/div/input")).sendKeys("cKUuIw7@o");
		driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/div[2]/button")).click();
	}
	/*
	@Test
	public void testCriarConta() throws Exception {
		driver.get("https://dev.e-rentav.com/173/banking/accounts/create");
		WebElement campo = driver.findElement(By.name("palavra-termo"));
		campo.clear();
		campo.sendKeys("bolsas");
		campo.submit();
		System.out.println(driver.getTitle());
		assertTrue(driver.getTitle().contains("Todas as notícias | Metrópole Digital"));
	}
	
	@Test
    public void selectOption() {
		driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        WebElement selectElement = driver.findElement(By.name("selectomatic"));
        Select select = new Select(selectElement);

        WebElement twoElement = driver.findElement(By.cssSelector("option[value=two]"));
        WebElement fourElement = driver.findElement(By.cssSelector("option[value=four]"));
        WebElement countElement = driver.findElement(By.cssSelector("option[value='still learning how to count, apparently']"));

        select.selectByVisibleText("Four");
        Assert.assertTrue(fourElement.isSelected());

        select.selectByValue("two");
        Assert.assertTrue(twoElement.isSelected());

        select.selectByIndex(3);
        Assert.assertTrue(countElement.isSelected());
    }
	*/
	@Test
	public void testCadastroConta() {
		driver.findElement(By.xpath("//*[@id=\"sidenav-collapse-main\"]/ul/li[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"navbar-cadastro\"]/ul/li[1]/a/span")).click();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/a")).click();
		
		//espera 5 segundos para evitar erro de não carregamento no elemento ul, observado que alguns elementos 
		//carregam depois da página
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//select corretora      
		driver.findElement(By.xpath("//*[@id=\"account\"]/div[1]/div/div[1]/div/div/div/input")).click();
		driver.findElement(By.xpath("//*[@id=\"leftMenu\"]/div[3]/div[1]/div[1]/ul/li[5]")).click();
		//nome da conta
		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("nome da conta");
		//numero da conta
		driver.findElement(By.xpath("//*[@id=\"account_number\"]")).sendKeys("numero da conta");
		//proporção
		driver.findElement(By.xpath("//*[@id=\"account\"]/div[1]/div/div[4]/div/input")).sendKeys("100");
		//alavancagem
		driver.findElement(By.xpath("//*[@id=\"leverage\"]")).sendKeys("1");
		//submit
		driver.findElement(By.xpath("//*[@id=\"leverage\"]")).submit();
		
	}
	@Test
	public void testListarRemessas() {
		driver.findElement(By.xpath("//*[@id=\"sidenav-collapse-main\"]/ul/li[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"navbar-cadastro\"]/ul/li[1]/a/span")).click();
		driver.findElement(By.xpath("//*[@id=\"navbar-cadastro\"]/ul/li[2]")).click();
		//driver.findElement(By.xpath("//*[@id=\"navbar-cadastro\"]/ul/li[1]/a/span")).click();
		WebElement tableCorretora = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/table/thead/tr/th[1]"));
		System.out.println(tableCorretora.getText());
		//Assert.assertEquals(tableCorretora.getText(), "Corretora");
		
	}
	

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
