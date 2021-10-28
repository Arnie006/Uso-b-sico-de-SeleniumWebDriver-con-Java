package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.PublicKey;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ch   rome.ChromeDriver;

/**
 * Unit test for simple App.
 */
@RunWith(DataProviderRunner.class)
public class AppTest 
{
    private WebDriver driver;
    By edadLocalizador = By.name("edad");
    By generoLocalizador = By.name("genero");
    By btn_consultarLocalizador = By.name("btn_consultar");
    By bodySelector = By.cssSelector("body");

    @DataProvider
    public static Object [][] proveedorDatos(){
        return new Object[][]{
            {"masculino", "18", "El valor de la prima anual de seguro de auto según su género y edad es: 2000 dólares"},
            {"masculino", "24", "El valor de la prima anual de seguro de auto según su género y edad es: 2000 dólares"},
            {"masculino", "25", "El valor de la prima anual de seguro de auto según su género y edad es: 1000 dólares"},
            {"masculino", "64", "El valor de la prima anual de seguro de auto según su género y edad es: 1000 dólares"}
        };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost");
    }
    @Test
    @UseDataProvider("proveedorDatos")
    public void testConsultarPrimaAnual(String genero, String edad, String salida_esperada) throws InterruptedException
    {
            Thread.sleep(1000);
            driver.findElement(generoLocalizador).sendKeys(genero);
            driver.findElement(edadLocalizador).sendKeys(edad);
            driver.findElement(btn_consultarLocalizador).click();
            assertEquals(salida_esperada, driver.findElement(bodySelector).getText());
    }
    @After
    public void tearDown(){
    driver.quit();
    //driver.quit() 
    }
}
