package br.com.devwell.task.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static br.com.devwell.task.functional.utils.DateUtils.oneFutureDateString;
import static br.com.devwell.task.functional.utils.DateUtils.onePastDateString;


@RunWith(Parameterized.class)
public class CreateTaskTest {

    @Parameter
    public String todoName;

    @Parameter(value = 1)
    public String dateString;

    @Parameter(value = 2)
    public String expectedMessage;

    @Parameter(value = 3)
    public String caseTest;


    public WebDriver getWebDriver() throws MalformedURLException {
        //open driver seleniumm
        //WebDriver webDriver =new ChromeDriver();
        WebDriver webDriver =new RemoteWebDriver(new URL("http://192.168.10.20:4444/wd/hub"),new ChromeOptions());
        webDriver.navigate().to("http://192.168.10.20:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }


    @Parameters(name = "{3}")
    public static Collection<Object[]> testParameters(){
        return Arrays.asList(
          new Object[][]{
                  {"",onePastDateString(),"Fill the task description","shouldThrowsFillDateExptionEmpty"},
                  {"todoTask",onePastDateString(),"Due date must not be in past","shouldThrowsPasteDate"},
                  {"todoTask","","Fill the due date","shouldThrowsTodoNull"},
                  {"todoTask",oneFutureDateString(),"Success!","shouldSaveTaskSucess"}
          }
        );
    }

    @Test
    public void parametrizedTestRun() throws MalformedURLException {
        WebDriver webDriver = getWebDriver();
        try{
            //click add todo
            webDriver.findElement(By.id("addTodo")).click();
            //set task name
            webDriver.findElement(By.id("task")).sendKeys(todoName);
            //set task date
            webDriver.findElement(By.id("dueDate")).sendKeys(dateString);
            //click save task
            webDriver.findElement(By.id("saveButton")).click();

            String message = webDriver.findElement(By.id("message")).getText();

            Assert.assertEquals(expectedMessage,message);

        }finally {
            webDriver.quit();
        }
    }


}
