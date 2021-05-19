package br.com.devwell.task.functional;

import br.com.devwell.task.functional.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static br.com.devwell.task.functional.utils.DateUtils.oneFutureDateString;

public class TaskTest {

    WebDriver webDriver;

    @Before
    public void setWebDriver(){
        webDriver =new ChromeDriver();
    }

    @Test
    public void shouldCreateTask(){

        //open driver seleniumm
        webDriver.navigate().to("http://localhost:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //click add todo
        webDriver.findElement(By.id("addTodo")).click();
        //set task name
        webDriver.findElement(By.id("task")).sendKeys("Minha nova tarefa");
        //set task date
        webDriver.findElement(By.id("dueDate")).sendKeys(oneFutureDateString());
        //click save task
        webDriver.findElement(By.id("saveButton")).click();

        Assert.assertEquals("Success!",webDriver.findElement(By.id("message")).getText());

        webDriver.quit();

    }
}
