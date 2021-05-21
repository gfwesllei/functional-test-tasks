package br.com.devwell.task.functional;

import br.com.devwell.task.functional.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static br.com.devwell.task.functional.utils.DateUtils.oneFutureDateString;

public class DeleteTaskTests {

    public static final String SUCCESS = "Success!";

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
            //ad task first
            webDriver.findElement(By.id("addTodo")).click();
            webDriver.findElement(By.id("task")).sendKeys("new Task");
            webDriver.findElement(By.id("dueDate")).sendKeys(oneFutureDateString());
            webDriver.findElement(By.id("saveButton")).click();
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals(SUCCESS,message);
            //click remove link
            webDriver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
            message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals(SUCCESS,message);

        }finally {
            webDriver.quit();
        }
    }
}
