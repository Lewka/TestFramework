package auto.steps;

import static auto.core.Wrapper.open;

public class MainPageStep extends BaseStep {

    private static final String MAIN_PAGE_URL = "https://www.tinkoff.ru/";

    public MainPageStep openMainPage() {
        LOGGER.info("Opening main page");
        open(MAIN_PAGE_URL);
        return this;
    }

}