/**
 * A Class to define ColoredCard
 */
public class ColoredCard extends Card{
    //the first character of the color
    private char color;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public ColoredCard(char color){
        super();
        if(colorCheck(color))
            this.color = color;
        else
            System.out.println("invalid color");
    }

    private boolean colorCheck(char color){
        char[] colors = {'r','y','g','b','n'};
        for (char c: colors) {
            if(color == c)
                return true;
        }
        return false;
    }

    /**
     * A method to return the color of card
     * @return the color of card
     */
    public char getColor(){
        return color;
    }

}
