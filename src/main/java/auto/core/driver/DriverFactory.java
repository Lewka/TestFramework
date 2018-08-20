package auto.core.driver;

import auto.core.utils.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import static auto.core.utils.ResourcesReader.getBrowser;

public class DriverFactory {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
    };

    private DriverFactory() {
    }

    private WebDriver initDriver() {
        Browser browser = getBrowser();
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriverProvider().createWebDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriverProvider().createWebDriver();
            default:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriverProvider().createWebDriver();
        }
    }

    public synchronized static void driverQuit() {
        threadDriver.get().quit();
        threadDriver.set(null);
    }

    public synchronized static WebDriver getThreadDriver() {
        if (threadDriver.get() == null) {
            threadDriver.set(new DriverFactory().initDriver());
        }
        return threadDriver.get();
    }

}