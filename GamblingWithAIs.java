import java.util.*;

public class GamblingWithAIs {
    //In this game, you and some other AI players draw cards from a standard deck of 52 cards. Whenever you throw a card on the
    //table, all players with a lower value than you will have to also throw their cards into the pile of cards in the middle.
    //Whenever a player has no cards, they lose. The last player remaining wins.
    public List<String> cardsInMiddleOfTable = new ArrayList<String>();
    public List<String> suits = new ArrayList<String>();
    public List<String> cardsInSuit = new ArrayList<String>();
    public List<String> player1 = new ArrayList<>();
    public List<String> player2 = new ArrayList<>();
    public List<String> you = new ArrayList<>();
    public List<List<String>> players = new ArrayList<>();
    public boolean player1_status;
    public boolean player2_status;
    Random random = new Random();
    //constructor
    public void GamblingWithAIS() {
        //initialize player status
        player1_status = true;
        player2_status = true;
        //initialize players HashSet
        players.add(you);
        players.add(player1);
        players.add(player2);
        //add things to suits.
        suits.add("Clubs");
        suits.add("Diamonds");
        suits.add("Spades");
        suits.add("Hearts");
        //add things to cardsInSuit
        cardsInSuit.add("Ace");
        cardsInSuit.add("2");
        cardsInSuit.add("3");
        cardsInSuit.add("4");
        cardsInSuit.add("5");
        cardsInSuit.add("6");
        cardsInSuit.add("7");
        cardsInSuit.add("8");
        cardsInSuit.add("9");
        cardsInSuit.add("10");
        cardsInSuit.add("Jack");
        cardsInSuit.add("Queen");
        cardsInSuit.add("King");
        //add things to cardsInMiddleOfTable
        for (String suit : suits) {
            for (String cardInSuit : cardsInSuit) {
                String card = cardInSuit + " of " + suit;
                cardsInMiddleOfTable.add(card);
            }
        }
    }

    public int getPlayer1() {
        return player1.size();
    }

    public int getPlayer2() {
        return player2.size();
    }

    public int getYourLength() {
        return you.size();
    }

    public String getYourCards() {
        String yourCards = "";
        for (String card : you) {
            yourCards += card + ",";
        }
        return yourCards;
    }

    public void drawCard(List<String> player) {
        int randomIndex = random.nextInt(cardsInMiddleOfTable.size() - 1);
        String randomCard = cardsInMiddleOfTable.get(randomIndex);
        player.add(randomCard);
        cardsInMiddleOfTable.remove(randomCard);
    }

    public void putCardOnTable(List<String> player, String cardOnTable) {
        player.remove(cardOnTable);
        cardsInMiddleOfTable.add(cardOnTable);
        String[] wordsInCardOnTable = cardOnTable.split(" ");
        String typeOfCardOnTable = wordsInCardOnTable[0];
        for (List<String> playerCards : players) {
            if (!playerCards.equals(player)) {
                for (int index = 0; index < playerCards.size();) {
                    String card = playerCards.get(index);
                    String[] wordsInCard = card.split(" ");
                    String typeOfCard = wordsInCard[0];
                    if (cardsInSuit.indexOf(typeOfCard) < cardsInSuit.indexOf(typeOfCardOnTable)) {
                        playerCards.remove(card);
                        cardsInMiddleOfTable.add(card);
                    } else{
                        index += 1;
                    }
                }
            }
        }
    }

    public void distributeCards(int numOfCards) {
        for (List<String> playerCards : players) {
            for (int i = 1; i <= numOfCards; i += 1) {
                drawCard(playerCards);
            }
        }
    }

    public String aiDecision(List<String> player) {
        int maxCardRank = -1;
        String maxCard = null;
        for (String card : player) {
            String typeOfCard = card.split(" ")[0];
            int cardRank= cardsInSuit.indexOf(typeOfCard);
            if (cardRank > maxCardRank) {
                maxCardRank = cardRank;
                maxCard = card;
            }
        }
        if (player.size() == 1 || maxCardRank <= 5) {
            drawCard(player);
            return " drawn a card.";
        } else{
            putCardOnTable(player, maxCard);
            return " put " + maxCard + " on the table.";
        }
    }
}