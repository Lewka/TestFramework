package auto.steps;

import auto.bo.KomPlatezhiCompany;
import auto.bo.Region;
import auto.page.KomPlatezhiPage;

public class KomPlatezhiStep extends BaseStep {

    private KomPlatezhiPage komPlatezhiPage = new KomPlatezhiPage();

    public boolean isRegionEqualsTo(Region region) {
        LOGGER.info("Checking is current region equals to [{}]", region);
        return komPlatezhiPage.isRegionEqualTo(region);
    }

    public KomPlatezhiStep selectRegion(Region region) {
        LOGGER.info("Selecting region to [{}]", region);
        if (!isRegionEqualsTo(region)) {
            komPlatezhiPage.selectRegion(region);
        }
        return this;
    }

    public KomPlatezhiPaymentStep selectCompany(KomPlatezhiCompany komPlatezhiCompany) {
        LOGGER.info("Selection company [{}]", komPlatezhiCompany);
        komPlatezhiPage.selectCompany(komPlatezhiCompany);
        return new KomPlatezhiPaymentStep();
    }

    public void selectCompany(String komPlatezhiCompany) {
        selectCompany(KomPlatezhiCompany.from(komPlatezhiCompany));
    }

    public String getFirstCompanyName() {
        LOGGER.info("Getting first companies name");
        return komPlatezhiPage.getFirstCompanyName();
    }

    public boolean isCompanyPresented(KomPlatezhiCompany komPlatezhiCompany) {
        LOGGER.info("Checking is [{}] company presented on the page", komPlatezhiCompany);
        return komPlatezhiPage.isCompanyPresented(komPlatezhiCompany);
    }

}