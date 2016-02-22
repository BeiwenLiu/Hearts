package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Beiwen Liu on 1/23/2016.
 */
public class GameRegulator {
    private int points;
    private Player player;
    private ArrayList<Card> deck;
    private int containerIndex; //This is used to keep track of the container
    private int index; // This is used to distribute cards to players
    private int imageIndex; //This is only used temporarily to assign
    //the corresponding card indicies to the corresponding imageView.
    CardIndexContainer[] container = new CardIndexContainer[4];
    private boolean isEmpty;

    public int getPoints() {
        return points;
    }

    private class CardIndexContainer {
        private Card card;
        private int index;

        public CardIndexContainer(Card card, int index) {
            this.card = card;
            this.index = index;
            isEmpty = true;
        }

        public CardIndexContainer() {
            this(null,0);
        }

        private void setCard(Card card) {
            this.card = card;
        }

        private void setPointer(int pointer) {
            this.index = pointer;
        }

        private Card getCard() {
            return card;
        }

        private int getIndex() {
            return index;
        }
    }


    public GameRegulator() {
        for (int i = 0; i < container.length; i++) {
            container[i] = new CardIndexContainer();
        }
    }

    public void shuffleDeck(Deck deck1) {
        deck = deck1.getCards();
        Random random = new Random();
        Card temp;
        index = 0;
        int index1 = 0;
        for (int i = deck.size() - 1; i > 0; i--) {
            index1 = random.nextInt(i + 1);
            temp = deck.get(index1);
            deck.set(index1, deck.get(i));
            deck.set(i,temp);
        }
    }

    public Card dealCard() {
        return deck.get(index++);
    }


    public int incrementPlayerRound(int i) {
        if (i >= 3) {
            return 0;
        } else {
            return i + 1;
        }
    }

    public void acceptCard(Card card, int pointer) {
        if (index >= 4) {
            index = 0;
        }
        isEmpty = false;
        container[index].setCard(card);
        container[index].setPointer(pointer);
        index++;
    }

    public int computeHighestPlayer() { //This will determine who will go next. AKA who played the highest card.
        index = 0;
        ArrayList<Card> cardsWithPoints = new ArrayList<>();
        CardIndexContainer max = container[0];
        cardsWithPoints.add(container[0].getCard());
        for (int i = 1; i < container.length; i++) {
            cardsWithPoints.add(container[i].getCard());
            if (container[i].getCard().getSuit().equals(max.getCard().getSuit())) {
                if (container[i].getCard().getCardValue() > max.getCard().getCardValue()) {
                    max = container[i];
                }
            }
        }
        points = calculatePoints(cardsWithPoints);
        return max.getIndex();
    }

    private int calculatePoints(ArrayList<Card> cardsWithPoints) {
        int total = 0;
        for (Card x : cardsWithPoints) {
            if (x.getSuit().equals("Hearts")) {
                total++;
            } else if (x.getSuit().equals("Spades") && x.getValue().equals("Queen")) {
                total += 13;
            }
        }
        return total;
    }

    public ArrayList<Integer> calculateRestrictions(int pointer) {
        ArrayList<Integer> restriction = new ArrayList<>();
        ArrayList<Card> hand = Main.players[pointer].returnHand();
        Card target = null;
        if (!isEmpty) {
            target = container[0].getCard();
        }
        if (target == null) {
            for (int i = 0; i < hand.size(); i++) {
                if (!(hand.get(i).toString().equals("TwoOfClubs"))) {
                    restriction.add(i);
                }
            }
        } else {
            for (int i = 0; i < hand.size(); i++) {
                if (!(target.getSuit().equals(hand.get(i).getSuit()))
                        || hand.get(i).getPlayed()) {
                    restriction.add(i);
                }
                if (restriction.size() == 13) {
                    return null;

                }
            }
        }
        switch (pointer) {
            case 1:
                for (int i = 0; i < restriction.size();i++) {
                    restriction.set(i, restriction.get(i) + 13);
                }
                break;
            case 2:
                for (int i = 0; i < restriction.size();i++) {
                    restriction.set(i, restriction.get(i) + 26);
                }
                break;
            case 3:
                for (int i = 0; i < restriction.size();i++) {
                    restriction.set(i, restriction.get(i) + 39);
                }
        }

        return restriction;

    }

    public void computeShootMoon() {
        if (Main.shootersChoice == false) {
            boolean shotMoon = false;
            for (int i = 0; i < Main.players.length; i++) {
                if (Main.players[i].getPoints() == 26) {
                    shotMoon = true;
                }
            }
            if (shotMoon) {
                for (int i = 0; i < Main.players.length; i++) {
                    if (Main.players[i].getPoints() != 26) {
                        Main.players[i].clearPoints();
                        Main.players[i].setPoints(26);
                    } else {
                        Main.players[i].clearPoints();
                    }
                }
            }
        }
    }


    public Card assignCard() {
        return deck.get(imageIndex++);
    }
}
