/**
 * A class that define each block of board and the plate its on
 */
public class Block {
    private char color;
    private int positionInPlate;
    private int plateNum;

    /**
     * A constructor to make a block
     * @param position position of block in plate
     * @param plateNum the number of it's plate
     */
    public Block(int position,int plateNum){
        color = 'n'; //first the block has no color
        positionInPlate = position;
        this.plateNum = plateNum;
    }
    public char getColor(){
        return color;
    }
    /**
     * A method to change the color of block from outside of class
     * @param c the new color
     */
    public void setColor(char c){
        this.color = c;
    }
}
