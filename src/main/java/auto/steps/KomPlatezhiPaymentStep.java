package auto.steps;

import auto.bo.ErrorMessage;
import auto.bo.KomPlatezhiPayment;
import auto.page.KomPlatezhiPaymentPage;

public class KomPlatezhiPaymentStep extends BaseStep {

    private KomPlatezhiPaymentPage komPlatezhiPaymentPage = new KomPlatezhiPaymentPage();

    public KomPlatezhiPaymentStep fillFields(KomPlatezhiPayment komPlatezhiPayment) {
        LOGGER.info("Filling payments fields with");
        komPlatezhiPaymentPage.fillFields(komPlatezhiPayment);
        return this;
    }

    public boolean isWrongProviderPayerCodeErrorPresented(ErrorMessage errorMessage) {
        LOGGER.info("Checking is wrong provider payer code error presented on the page");
        return komPlatezhiPaymentPage.isWrongProviderPayerCodeErrorPresented(errorMessage);
    }

    public boolean isWrongProviderPeriodErrorPresented(ErrorMessage errorMessage) {
        LOGGER.info("Checking is wrong provider period error presented on the page");
        return komPlatezhiPaymentPage.isWrongProviderPeriodErrorPresented(errorMessage);
    }

    public boolean isWrongAmountOfPaymentErrorPresented(ErrorMessage errorMessage) {
        LOGGER.info("Checking is wrong amount of payment error presented on the page");
        return komPlatezhiPaymentPage.isWrongAmountOfPaymentErrorPresented(errorMessage);
    }

    public boolean isCorrectCompanyPageOpened(String companyName) {
        LOGGER.info("Checking is opened page is [{}] companies or not", companyName);
        return komPlatezhiPaymentPage.isCorrectCompanyPageOpened(companyName);
    }

}