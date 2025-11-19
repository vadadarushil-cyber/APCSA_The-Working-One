package cards;

public class Deck {

    private Card[] deck;
    private int top;

    public Deck() {
        deck = new Card[52];
        int index = 0;

        for (int s = 0; s < 4; s++) {
            for (int v = 0; v < 13; v++) {
                deck[index] = new Card(s, v);
                index++;
            }
        }

        top = 0; 
    }

    public void shuffle() {
        top = 0;

        for (int i = deck.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); 
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    public void cut(int index) {
        if (index < 0) {
            index = 0;
        }
        if (index > 52) {
            index = 52;
        }
        if (top != 0) {
            return;
        }

        Card[] newDeck = new Card[52];
        int newPos = 0;

        for (int i = index; i < 52; i++) {
            newDeck[newPos] = deck[i];
            newPos++;
        }
        for (int i = 0; i < index; i++) {
            newDeck[newPos] = deck[i];
            newPos++;
        }

        deck = newDeck;
        top = 0;
    }
    public Card draw() {
        if (top >= deck.length) {
            return null; 
        }
        Card c = deck[top];
        top++;
        return c;
    }

    public void print(int count) {
        if (count < 0) {
            count = 0;
        }
        if (count > deck.length - top) {
            count = deck.length - top;
        }

        for (int i = 0; i < count; i++) {
            Card c = deck[top + i];
            System.out.print(c.toString());
            if (i < count - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
