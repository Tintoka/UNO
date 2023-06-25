import java.util.ArrayList;

/**
 * A class to define players hand
 */
public class Player {
    private ArrayList<Card> cards;
    private int score;

    /**
     * constructor to creat a list for player's cards
     */
    public Player(){
        cards = new ArrayList<>();
        score = 0;
    }

    /**
     * A method to add card to player's hand
     * @param c the card to be added
     */
    public void addCard(Card c){
        cards.add(c);
    }

    /**
     * A method to remove a card from player's hand
     * @param c th ecard to be remove
     */
    public void removeCard(Card c){
        cards.remove(c);
    }

    /**
     * A method to return player's cards
     * @return cards
     */
    public ArrayList<Card> getCards(){
        return cards;
    }

    /**
     * A method to set score of the player (the play Class set it)
     * @param score the score to be set as
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * A method to get player's score
     * @return the score of player
     */
    public int getScore(){
        return score;
    }
}
