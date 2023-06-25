import java.util.Random;

/**
 * A class to define computer opponent
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(){
      super();
    }


    public Card choice(Card currentCard,char currentColor){
        if(currentCard instanceof WildCard)
            for (Card c: super.getCards()) {
                if(c instanceof ColoredCard)
                    if(((ColoredCard)c).getColor() == currentColor) {
                        Card res = c;
                        super.removeCard(c);
                        return res;
                    }
            }
        else if(currentCard instanceof NumericCard){
            for (Card c: super.getCards()){
                if(c instanceof NumericCard && (((ColoredCard)c).getColor() == currentColor || ((NumericCard)c).getNumber().equals(((NumericCard) currentCard).getNumber()))){
                    Card res = c;
                    super.removeCard(c);
                    return res;
                }
            }
        }
        else if(currentCard instanceof EffectCard){
            for (Card c: super.getCards()){
                if(c instanceof EffectCard &&(((ColoredCard)c).getColor() == currentColor || ((EffectCard)c).getEffect().equals(((EffectCard) currentCard).getEffect()))){
                    Card res = c;
                    super.removeCard(c);
                    return res;
                }
        }
    }
        else {
            for (Card c: super.getCards()) {
                if(c instanceof WildCard) {
                    Card res = c;
                    super.removeCard(c);
                    return res;
                    }
                }
            }
        return (new NumericCard(-1,'n'));
        }
        public char colorChoice(char currentColor){
        char[] colors = {'r','y','b','g'};
        Random rand = new Random();
        int res;
        do {
             res = rand.nextInt(4);
        }while(colors[res] == currentColor);
            return colors[res];
        }
}
