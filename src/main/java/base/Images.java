package base;

public enum Images {
    SMALL_IMAGE("src/test/resources/Screen 1_100kb.jpg"),
    MEDIUM_IMAGE("src/test/resources/Screen 2_7Mb.jpg"),
    PIXEL_1X1(""),
    IMAGE_URL("https://images.wallpaperscraft.ru/image/doroga_gory_tuman_219202_1024x768.jpg");

    private String path;
    Images(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
