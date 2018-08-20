package auto.core.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.util.Collections.singletonList;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;
import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

public class ChromeDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setUnhandledPromptBehaviour(DISMISS);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability("chrome.switches", singletonList("-- ignore-certificate-errors,--web-security=false,--ssl-protocol=any,--ignore-ssl-errors=true"));
        desiredCapabilities.setCapability(CAPABILITY, options);

        return new ChromeDriver(options);
    }

}