package br.com.devwell.task.prod;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HealthCheckIT {

    @Test
    public void healthCheckTest() throws MalformedURLException {
        Capabilities capbilities = DesiredCapabilities.chrome();
        WebDriver webDriver =new RemoteWebDriver(new URL("http://192.168.10.20:4444/wd/hub"),capbilities);

        try{
            webDriver.navigate().to("http://192.168.10.20:9999/tasks");
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String version = webDriver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build"));
        }finally {
            webDriver.quit();
        }

    }
}
