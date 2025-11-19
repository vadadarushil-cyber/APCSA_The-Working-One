package cards;

public class Card {

    private int suit;
    private int value;

    private static String[] SUIT_STRINGS = {"♦", "♣", "♥", "♠"};
    private static String[] VALUE_STRINGS =
            {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Card(int suit, int value) {

        if (suit < 0) {
            this.suit = 0;
        } else if (suit > 3) {
            this.suit = 3;
        } else {
            this.suit = suit;
        }

        if (value < 0) {
            this.value = 0;
        } else if (value > 12) {
            this.value = 12;
        } else {
            this.value = value;
        }
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        String suitStr = SUIT_STRINGS[suit];
        String valueStr = VALUE_STRINGS[value];
        return suitStr + valueStr;
    }
}
