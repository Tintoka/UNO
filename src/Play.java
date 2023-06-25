import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * A class to check the rules and give card to players
 */
public class Play {
    private LinkedList<Card> allCards;
    private Card currentCard;
    private char currentColor;
    private ArrayList<Player> players;
    /**
     * A constructor to creat a full set of card for play
     */
    public Play(int playerNumber){
        //Creat a full deck
        allCards = new LinkedList<>();
        char[] colors = {'r','y','g','b'};
        for(int i = 0; i < 4; i++){
            allCards.add(new WildCard("color"));
            allCards.add(new WildCard("draw"));
        }
        for (char c: colors) {
            for(int i = 0 ; i < 2; i++){
                for(int j = 1; j < 10; j++)
                    allCards.add(new NumericCard(j,c));
                allCards.add(new EffectCard("ban",c));
                allCards.add(new EffectCard("switchTurn",c));
                allCards.add(new EffectCard("draw",c));
            }
            allCards.add(new NumericCard(0,c));
        }
        //create playerList
        players = new ArrayList<>();
        for(int i = 0; i < playerNumber - 1;i++)
            players.add(new ComputerPlayer());
        players.add(new Player());
        //give card to each player
        for (Player p : players) {
            giveCard(7,p);
        }
        Random rand = new Random();
        int r;
        do {
             r = rand.nextInt(allCards.size());
        }while(!(allCards.get(r) instanceof  NumericCard));
        currentCard = allCards.get(r);
        currentCard.setFaceDown(false);
        currentColor = ((ColoredCard)currentCard).getColor();
    }

    /**
     * A method to calculate and set the score of player
     * @param p the player yo calculate it's score
     */
    public void scoreCalculator(Player p){
        for (Card c: p.getCards()) {
            if( c instanceof WildCard)
                p.setScore(p.getScore() + 50);
            else if(c instanceof NumericCard) {
                NumericCard nCard = (NumericCard) c;
                p.setScore(p.getScore() + Integer.valueOf(nCard.getNumber()));
            }
            else if(c instanceof EffectCard)
                p.setScore(p.getScore() + 20);
        }
    }

    /**
     * A method to give specific number of card to specific player
     * @param num tha number of card to give to player
     * @param p the player to be given the cards
     */
    public void giveCard(int num, Player p){
        for(int i = 0 ; i < num ; i++){
            int rand;
            do {
                rand =(int)( Math.random() * (allCards.size()));
            }while(rand > allCards.size());
            p.addCard(allCards.get(rand));
            allCards.remove(rand);
        }
    }

    public boolean putCard(Player p ,Card c){
            if(validCard(c)){
                currentCard = c;
                currentCard.setFaceDown(false);
                p.removeCard(c);
                if(!(c instanceof  WildCard)) {
                    currentColor = ((ColoredCard) c).getColor();
                }
                else if (p instanceof ComputerPlayer){
                    currentColor = ((ComputerPlayer)p).colorChoice(currentColor);
                }
                else{
                    System.out.println("Enter valid color");
                    Scanner scanner = new Scanner(System.in);
                    currentColor = scanner.next().charAt(0);
                }
                return true;
            }
            else{
                System.out.println("Invalid Card");
                return false;
            }

    }

    private boolean validCard(Card c){
            if(currentCard instanceof WildCard || c instanceof WildCard ||(c instanceof ColoredCard && ((ColoredCard ) c).getColor() == currentColor ))
                    return true;
            else if(typeCheck(c))
                if(c instanceof NumericCard && !(((NumericCard) c).getNumber().equals("-1")))
                    if(((NumericCard) c).getNumber().equals(((NumericCard)currentCard).getNumber()))
                        return true;
                    else;
                else if(c instanceof EffectCard)
                    return (((EffectCard) c).getEffect().equals(((EffectCard)currentCard).getEffect()));
               return false;
    }

    public boolean typeCheck(Card c){
        if(c instanceof WildCard || c instanceof ColoredCard && currentColor== ((ColoredCard) c).getColor())
            return true;
        else if(c instanceof NumericCard && currentCard instanceof NumericCard)
            return true;
        else return (c instanceof EffectCard && currentCard instanceof EffectCard);
    }

    public Card getCurrentCard(){
            return currentCard;
    }
    public char getCurrentColor(){
        return currentColor;
    }
    public void printAllCards(){
        for (Player p: players) {
            int i = 0;
            if(p instanceof ComputerPlayer){
                for (Card c: p.getCards()) {
                    System.out.println(i + ")");
                    c.printCard();
                    System.out.println( );
                    i++;
                }
            }
            else{
                for (Card c: p.getCards()) {
                    c.setFaceDown(false);
                    System.out.println(i + ")");
                    c.printCard();
                    System.out.println();
                    i++;
                }
            }
            System.out.println("********************************");
        }
        System.out.println("current card : ");
        currentCard.printCard();
    }
    public boolean winCheck(){
        for (Player p: players) {
            if(p.getCards().size() == 0)
                return true;
        }
        return false;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public boolean availableMove(Player p){
        for (Card c:p.getCards()) {
            if(validCard(c))
                return true;
        }
        return false;
    }
}
