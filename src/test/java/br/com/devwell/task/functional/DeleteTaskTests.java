package br.com.devwell.task.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DeleteTaskTests {

    public WebDriver getWebDriver() throws MalformedURLException {
        //open driver seleniumm
        WebDriver webDriver =new RemoteWebDriver(new URL("http://192.168.10.20:4444/wd/hub"),new ChromeOptions());
        webDriver.navigate().to("http://192.168.10.20:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }

    @Test
    public void deleteTaskTest() throws MalformedURLException {
        WebDriver webDriver = getWebDriver();
        try{
            //click add todo
            webDriver.findElement(By.linkText("Remove")).click();

            String message = webDriver.findElement(By.id("message")).getText();

            Assert.assertEquals("Success!",message);

        }finally {
            webDriver.quit();
        }
    }
}
