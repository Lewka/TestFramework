package auto.core.utils;

public enum LocationConstant {
    SUBFOLDER_SCREENSHOTS("./screenshots");

    private String path;

    LocationConstant(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}