public class Card {
    private boolean faceDown;
    //when a card is first made,it's faceDown
    public Card(){
        faceDown = true;
    }

    public void printCard(){
        if(faceDown){
            System.out.println("[           |");
            System.out.println("| FACE DOWN |");
            System.out.println("|           ]");
        }

        else
            System.out.print("faceUp Card ");
    }

    public void setFaceDown(boolean choice){
        faceDown = choice;
    }
    public boolean getFaceDown(){
        return faceDown;
    }

}
