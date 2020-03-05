package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {

		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);

		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);

		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	static public void checkMarkDeletion(WebDriver driver, String typeBuscar, String textBuscar, int index,
			String typePagina, String textPagina, int index2, String textComprobar, int timeout) {
		PO_View.checkElement(driver, typeBuscar, textBuscar).get(index).click();
		PO_View.checkElement(driver, typePagina, textPagina).get(index2).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, textComprobar, PO_View.getTimeout());
	}

}