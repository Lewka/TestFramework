package auto.steps;

import auto.bo.KomPlatezhiPayment;
import auto.page.KomPlatezhiPaymentPage;

public class KomPlatezhiPaymentStep extends BaseStep {

    private KomPlatezhiPaymentPage komPlatezhiPaymentPage = new KomPlatezhiPaymentPage();

    public KomPlatezhiPaymentStep fillFields(KomPlatezhiPayment komPlatezhiPayment) {
        komPlatezhiPaymentPage.fillFields(komPlatezhiPayment);
        return this;
    }

    public boolean isWrongProviderPayerCodeErrorPresented() {
        return komPlatezhiPaymentPage.isWrongProviderPayerCodeErrorPresented();
    }

    public boolean isWrongProviderPeriodErrorPresented() {
        return komPlatezhiPaymentPage.isWrongProviderPeriodErrorPresented();
    }

    public boolean isWrongAmountOfPaymentErrorPresented() {
        return komPlatezhiPaymentPage.isWrongAmountOfPaymentErrorPresented();
    }

    public boolean isCorrectCompanyPageOpened(String companyName) {
        return komPlatezhiPaymentPage.isCorrectCompanyPageOpened(companyName);
    }

}