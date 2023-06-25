/**
 * A class to define a WildCard, it can be colorWild or DrawWild
 */
public class WildCard extends Card {
    private String type;

    /**
     * A constructor to make a wildCard
     * @param type to define if it's colorWild or DrawWild
     */
    public WildCard(String type){
        if(typeCheck(type))
            this.type = type;
        else
            System.out.println("invalid type for wild card");
    }

    /**
     * A method to check if the type in constructor is valid
     * @param type the type to be check
     * @return true if it's valid,false if it's not
     */
    private boolean typeCheck(String type){
        String[] valid = {"color","draw"};
        for (String s: valid) {
            if(s.equals(type))
                return true;
        }
        return false;
    }

    /**
     * A method to return wildCard's type
     * @return the type of wildCard
     */
    public String getType(){
        return type;
    }

    @Override
    public void printCard(){
       if(super.getFaceDown())
           super.printCard();
        else{
            switch(type){
            case("color") : {
                System.out.println("[NO COLOR    |");
                System.out.println("|  NO COLOR  |");
                System.out.println("|    NO COLOR]");
                break;
            }
            case("draw") : {
                System.out.println("[4+          |");
                System.out.println("|     4+     |");
                System.out.println("|          4+]");
                break;
            }
            }
        }
    }
}
