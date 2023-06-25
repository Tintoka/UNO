/**
 * A class to define a numeric card
 */
public class NumericCard extends ColoredCard{
    private String number;

    public NumericCard(int num,char color){
        super(color);
        if(numberCheck(num))
            number = Integer.toString(num);
        else
            System.out.println("invalid num");
    }

    private boolean numberCheck(int num){
        return (num > -2 && num < 10);
    }

    /**
     * A method to return Card's number
     * @return card's number
     */
    public String getNumber(){
        return number;
    }
    @Override
    public void printCard( ) {
        if (super.getFaceDown())
            super.printCard();
        else {
            System.out.println(colorCheck()+"[" + number + "        " + getColor() + "|");
            System.out.println("|     " + number + "    |");
            System.out.println("|" + getColor() + "        " + number + "]" + ANSI_RESET);
        }
    }
    private String colorCheck(){
        switch(getColor())  {
            case('r') :{
                return ANSI_RED;
            }
            case ('b') : {
                return ANSI_BLUE;
            }
            case('y') : {
                return ANSI_YELLOW;
            }
            case('g') : {
                return ANSI_GREEN;
            }
            default:
                return ANSI_RESET;
        }
    }
}
