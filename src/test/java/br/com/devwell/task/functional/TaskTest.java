package br.com.devwell.task.functional;

import br.com.devwell.task.functional.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static br.com.devwell.task.functional.utils.DateUtils.oneFutureDateString;
import static br.com.devwell.task.functional.utils.DateUtils.onePastDateString;


@RunWith(Parameterized.class)
public class TaskTest {

    @Parameter(value = 0)
    public String todoName;

    @Parameter(value = 1)
    public String dateString;

    @Parameter(value = 2)
    public String expectedMessage;

    @Parameter(value = 3)
    public String caseTest;

    public WebDriver webDriver;

    @Before
    public void setWebDriver(){
        //open driver seleniumm
        webDriver =new ChromeDriver();
        webDriver.navigate().to("http://localhost:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void closeDrive(){
        webDriver.quit();
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
    public void shouldCreateTask(){

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

    }
}
