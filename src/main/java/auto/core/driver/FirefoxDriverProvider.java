package auto.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;
import static org.openqa.selenium.firefox.FirefoxDriver.SystemProperty.BROWSER_LOGFILE;
import static org.openqa.selenium.remote.CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR;

public class FirefoxDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createWebDriver() {
        //using this parameter to turn off Firefox logging
        System.setProperty(BROWSER_LOGFILE, "/dev/null");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference(UNEXPECTED_ALERT_BEHAVIOUR, DISMISS.toString());

        FirefoxOptions options = new FirefoxOptions();

        options.addPreference("security.sandbox.content.level", 5);
        options.setProfile(profile);
        options.setAcceptInsecureCerts(true);

        return new FirefoxDriver(options);
    }
}