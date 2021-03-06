package test;

import auto.core.reporting.TestNgListener;
import auto.steps.KomPlatezhiStep;
import auto.steps.MainPageStep;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

import static auto.core.driver.DriverFactory.driverQuit;
import static auto.core.driver.DriverFactory.getThreadDriver;

@Listeners(TestNgListener.class)
public class BaseTest {

    protected MainPageStep mainPageStep = new MainPageStep();
    protected KomPlatezhiStep komPlatezhiStep = new KomPlatezhiStep();

    private static final int DEFAULT_WAIT_TIME = 5;

    @BeforeClass
    public void setUp() {
        getThreadDriver().manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driverQuit();
    }
}