package auto.core;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static auto.core.driver.DriverFactory.getThreadDriver;
import static auto.core.reporting.Logger.debug;

public final class Wrapper {

    private static final long DEFAULT_WAIT_TIME = 10L;

    private static final Logger LOGGER = LoggerFactory.getLogger(Wrapper.class);

    private Wrapper() {
    }

    public static void open(String absoluteUrl) {
        debug("Opening [{}] URL", absoluteUrl);
        getThreadDriver().navigate().to(absoluteUrl);
    }

    public static boolean waitForTitleToBe(String shouldBe) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), DEFAULT_WAIT_TIME);
        return wait.until(ExpectedConditions.titleContains(shouldBe));
    }

    public static void sleep(int sec) {
        try {
            LOGGER.debug("Waiting for [{}] sec", sec);
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
    }

}