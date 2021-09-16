package enums;

import lombok.Getter;

public enum Category {
    FOOD("Food", 1),
    ELECTRONICS("Electronic", 2),
    FURNITURE("Furniture", 3);

    @Getter
    public  String title;
    @Getter
    public int id;

    Category(String title, int id) {
        this.title = title;
        this.id = id;
    }
}