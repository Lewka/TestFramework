package auto.steps;

import auto.bo.KomPlatezhiPayment;
import auto.page.KomPlatezhiPaymentPage;

public class KomPlatezhiPaymentStep extends BaseStep {

    private KomPlatezhiPaymentPage komPlatezhiPaymentPage = new KomPlatezhiPaymentPage();

    public KomPlatezhiPaymentStep fillFields(KomPlatezhiPayment komPlatezhiPayment) {
        LOGGER.info("Filling payments fields with");
        komPlatezhiPaymentPage.fillFields(komPlatezhiPayment);
        return this;
    }

    public boolean isWrongProviderPayerCodeErrorPresented() {
        LOGGER.info("Checking is wrong provider payer code error presented on the page");
        return komPlatezhiPaymentPage.isWrongProviderPayerCodeErrorPresented();
    }

    public boolean isWrongProviderPeriodErrorPresented() {
        LOGGER.info("Checking is wrong provider period error presented on the page");
        return komPlatezhiPaymentPage.isWrongProviderPeriodErrorPresented();
    }

    public boolean isWrongAmountOfPaymentErrorPresented() {
        LOGGER.info("Checking is wrong amount of payment error presented on the page");
        return komPlatezhiPaymentPage.isWrongAmountOfPaymentErrorPresented();
    }

    public boolean isCorrectCompanyPageOpened(String companyName) {
        LOGGER.info("Checking is opened page is [{}] companies or not", companyName);
        return komPlatezhiPaymentPage.isCorrectCompanyPageOpened(companyName);
    }

}