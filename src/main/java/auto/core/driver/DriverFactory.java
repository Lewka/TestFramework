package auto.core.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import static auto.core.utils.ResourcesReader.getBroser;
import static java.util.Collections.singletonList;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;
import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;
import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;
import static org.openqa.selenium.firefox.FirefoxDriver.SystemProperty.BROWSER_LOGFILE;
import static org.openqa.selenium.firefox.GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR;

public class DriverFactory {

    private static final String PATH_TO_DRIVER = "data/3rdParty/";
    private static final String CHROMEDRIVER_EXE = "chromedriver";
    private static final String GECKO_EXE = "geckodriver.exe";

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
    };

    private DriverFactory() {
    }

    private WebDriver initDriver() {
        String capability = getBroser();
        WebDriver driver;
        if (capability.equalsIgnoreCase(FIREFOX)) {
            System.setProperty(GECKO_DRIVER_EXE_PROPERTY, PATH_TO_DRIVER + GECKO_EXE);
            //using this parameter to turn off Firefox logging
            System.setProperty(BROWSER_LOGFILE, "/dev/null");

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference(UNEXPECTED_ALERT_BEHAVIOUR, DISMISS.toString());

            FirefoxOptions options = new FirefoxOptions();

            options.addPreference("security.sandbox.content.level", 5);
            options.setProfile(profile);
            options.setAcceptInsecureCerts(true);
            driver = new FirefoxDriver(options);
        } else if (capability.equalsIgnoreCase(CHROME)) {
            System.setProperty(CHROME_DRIVER_EXE_PROPERTY, PATH_TO_DRIVER + CHROMEDRIVER_EXE);
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.setUnhandledPromptBehaviour(DISMISS);
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability("chrome.switches", singletonList("-- ignore-certificate-errors,--web-security=false,--ssl-protocol=any,--ignore-ssl-errors=true"));
            desiredCapabilities.setCapability(CAPABILITY, options);
            driver = new ChromeDriver(options);
        } else {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public synchronized static void driverQuit() {
        threadDriver.get().quit();
        threadDriver.set(null);
    }

    public synchronized static WebDriver getThreadDriver() {
        if (threadDriver.get() == null) {
            threadDriver.set(new DriverFactory().initDriver());
            return threadDriver.get();
        }
        return threadDriver.get();
    }

}