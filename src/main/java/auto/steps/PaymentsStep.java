package auto.steps;

import auto.page.PaymentsPage;

import static auto.bo.PaymentType.KOMMUNALNIE_PLATEZHI;

public class PaymentsStep extends BaseStep {

    private PaymentsPage paymentsPage = new PaymentsPage();

    public KomPlatezhiStep selectKommPlatezhi() {
        paymentsPage.selectPaymentType(KOMMUNALNIE_PLATEZHI);
        return new KomPlatezhiStep();
    }

    public PaymentsStep searchForCompany(String companyName) {
        paymentsPage.searchCompany(companyName);
        return this;
    }

    public KomPlatezhiPaymentStep openCompanyByName(String companyName) {
        paymentsPage.openCompanyByName(companyName);
        return new KomPlatezhiPaymentStep();
    }

    public boolean isSearchingCompanyFirstInList(String companyName) {
        return paymentsPage.isSearchingCompanyIsFirst(companyName);
    }

}