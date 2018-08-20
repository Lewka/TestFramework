package auto.core.utils;

public enum LocationConstant {
    PATH_TO_DATA("./data"),
    PATH_TO_REPORT("./report"),
    SUBFOLDER_SCREENSHOTS("./screenshots"),
    SUBFOLDER_EXTENT_REPORT("/extent"),
    SUBFOLDER_EMAIL_REPORT("/email");

    private String path;

    LocationConstant(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}