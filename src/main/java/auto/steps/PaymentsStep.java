package auto.steps;

import auto.page.PaymentsPage;

import static auto.bo.PaymentType.KOMMUNALNIE_PLATEZHI;

public class PaymentsStep extends BaseStep {

    private PaymentsPage paymentsPage = new PaymentsPage();

    public KomPlatezhiStep selectKommPlatezhi() {
        LOGGER.info("Selecting Komm Platezhi");
        paymentsPage.selectPaymentType(KOMMUNALNIE_PLATEZHI);
        return new KomPlatezhiStep();
    }

    public PaymentsStep searchForCompany(String companyName) {
        LOGGER.info("Searching for [{}] company", companyName);
        paymentsPage.searchCompany(companyName);
        return this;
    }

    public KomPlatezhiPaymentStep openCompanyByName(String companyName) {
        LOGGER.info("Opening company with name [{}]", companyName);
        paymentsPage.openCompanyByName(companyName);
        return new KomPlatezhiPaymentStep();
    }

    public boolean isSearchingCompanyFirstInList(String companyName) {
        LOGGER.info("Checking is company [{}] first in the list", companyName);
        return paymentsPage.isSearchingCompanyIsFirst(companyName);
    }

}