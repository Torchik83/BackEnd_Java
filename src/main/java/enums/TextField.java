package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TextField {

    RUSSIAN_TITLE("Продукты"),
    EMPTY(""),
    SPACE_FRONT_TITLE(" Food"),
    SPACE_TITLE(" "),
    CHAR_TITLE("/=+&*%$#@"),
    BIG_TITLE("O say, can you see, by the dawn’s early light," +
            "What so proudly we hailed at the twilight’s last gleaming?" +
            "Whose broad stripes and bright stars, through the perilous fight," +
            "O’er the ramparts we watched, were so gallantly streaming?" +
            "And the rockets’ red glare, the bombs bursting in air," +
            "Gave proof through the night that our flag was still there." +
            "O say does that star spangled banner yet wave" +
            "O’er the land of the free, and the home of the brave?"),
    INT_TITLE(2),
    ZERO(0),
    SPACE_BETWEEN_NUMBER(1 + " " + 2),
    NULL_ID(null),
    BOOL_ID(true),
    NULL(null);

    @Getter
    private int intTitle;
    @Getter
    private boolean boolTitle;
    @Getter
    private String strTitle;

    TextField(int title) {
        this.intTitle = title;
    }
    TextField(boolean title) {
        this.boolTitle = title;
    }
    TextField(String title) {
        this.strTitle = title;
    }
}
