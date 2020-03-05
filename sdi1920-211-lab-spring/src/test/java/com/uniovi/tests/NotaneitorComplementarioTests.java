package com.uniovi.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementarioTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	// static String PathFirefox65 = "C:\\Program Files\\Mozilla
	// Firefox\\firefox.exe";
	// static String Geckdriver024 =
	// "C:\\Path\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1
	// y desactivar las actualizacioens automáticas):

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\CMG\\Desktop\\Tercero\\2_Cuatrimestre\\SDI\\Laboratorio\\Material\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// Común a Windows y a MACOSX

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Registrar a un profesor
	// usuario de ROL Administrador 99999988F/123456
	@Test
	public void PR01() {

		PO_HomeView.loginForm(driver, "class", "btn btn-primary", "login", "99999988F", "123456");

		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teachers-menu')]/a");
		elementos.get(0).click();

		// Esperamos a aparezca la opción de añadir profesor:
		// //a[contains(@href,'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'teacher/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();
		// Vamos al formulario de registro
		PO_RegisterView.fillTeacherForm(driver, "77777778A", "Josefo", "Perez", "Cat 1");

		PO_View.checkElement(driver, "text", "Josefo");

	}

	// Inválidos nombre y categoría
	@Test
	public void PR02() {

		PO_HomeView.loginForm(driver, "class", "btn btn-primary", "login", "99999988F", "123456");

		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teachers-menu')]/a");
		elementos.get(0).click();

		// Esperamos a aparezca la opción de añadir profesor:
		// //a[contains(@href,'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'teacher/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();
		// Vamos al formulario de registro
		PO_RegisterView.fillTeacherForm(driver, "77777788A", "J", "Perez", "asdf");
		
		SeleniumUtils.textoPresentePagina(driver, "El nombre tiene que tener más de un caracter.");

	}
	
	// Sólo administradores pueden añadir a un profesor
	@Test
	public void PR03() {
		PO_HomeView.loginForm(driver, "class", "btn btn-primary", "login", "99999990A", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'teachers-menu')]/a");
		elementos.get(0).click();
		
		SeleniumUtils.textoNoPresentePagina(driver, "Añadir profesor");
	}

}
