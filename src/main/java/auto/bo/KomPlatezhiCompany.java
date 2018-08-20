package auto.bo;

public enum KomPlatezhiCompany {
    ZKHU("ЖКУ-Москва");

    String companyName;

    KomPlatezhiCompany(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public static KomPlatezhiCompany from(String companyName) {
        for (KomPlatezhiCompany value : KomPlatezhiCompany.values()) {
            if (value.getCompanyName().equalsIgnoreCase(companyName)) {
                return value;
            }
        }
        throw new RuntimeException("Couldn't find a company with name " + companyName);
    }
}