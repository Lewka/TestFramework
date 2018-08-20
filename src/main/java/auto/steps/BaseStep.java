package auto.steps;

import auto.page.MainPage;

public abstract class BaseStep {

    private MainPage mainPage = new MainPage();

    public PaymentsStep openPayments() {
        mainPage.openPayments();
        return new PaymentsStep();
    }

}