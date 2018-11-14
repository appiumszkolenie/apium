import com.google.common.util.concurrent.FluentFuture;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import java.net.URL;

public class AppiumTest {

    public AndroidDriver<AndroidElement> driver;
    public FluentWait<WebDriver> wait;
    public DesiredCapabilities capabilities;

    @Test
    public void testName(ITestContext context) throws MalformedURLException, InterruptedException {
        capabilities = new DesiredCapabilities();
       // capabilities.setCapability( "BROWSER_NAME",  "Android");
        capabilities.setCapability( "VERSION", "6.0.1");
        capabilities.setCapability(  "deviceName",  "Samsung j5");
        capabilities.setCapability( "udid", "177054d0");
        capabilities.setCapability( "platformName",  "Android");
        capabilities.setCapability("appPackage","com.android.vending");
        capabilities.setCapability("appActivity","com.android.vending.AssetBrowserActivity");
        capabilities.setCapability("autoGrantPermissions",true);

        //capabilities.setCapability("automationName","UiAutomator2");


        this.driver = new AndroidDriver<AndroidElement>(new URL(  "http://127.0.0.1:4729/wd/hub"),capabilities);

        this.wait = new WebDriverWait(driver,15)

        .ignoring(StaleElementReferenceException.class)
        .ignoring(NullPointerException.class)
        .ignoring(ClassCastException.class)
        .ignoring(NoSuchElementException.class);

        context.setAttribute("driver",this.driver);
        context.setAttribute("wait",this.wait);

        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("com.android.vending:id/li_title")));
        StoreStartPage  storeStartPage = new StoreStartPage(context);
        storeStartPage.isDisplayed();
        Thread.sleep(1000);

    }
}
