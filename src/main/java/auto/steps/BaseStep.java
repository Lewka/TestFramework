package auto.steps;

import auto.page.MainPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseStep {

    private MainPage mainPage = new MainPage();

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseStep.class);

    public PaymentsStep openPayments() {
        LOGGER.info("Opening Payments page");
        mainPage.openPayments();
        return new PaymentsStep();
    }

}