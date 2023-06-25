import java.util.ArrayList;
/**
 * A class to define each plate in the board
 */
public class Plate {
    private ArrayList<Block> blocks;
    private int num;

    /**
     * A constructor to make a plate and the block in it
     * @param num the number of plate
     */
    public Plate(int num){
        blocks = new ArrayList<>();
        this.num = num;
        for(int i = 1; i < 10; i++){
            blocks.add(new Block(i,num));
        }
    }
    /*private void swap(int pose1,int pose2){
        char c1 = blocks.get(pose1).getColor();
        blocks.get(pose1).setColor(blocks.get(pose2).getColor());
        blocks.get(pose2).setColor(c1);
    }*/

    /**
     * A method that changes blocks color this way : 1->3 , 3->9 , 9->7 , 7->1 ,
     * 2->6 , 6->8 , 8->4 , 4-> 2
     */
    public void rotate(){
        char c1 = blocks.get(0).getColor();
        char c2 = blocks.get(1).getColor();
        blocks.get(0).setColor(blocks.get(6).getColor());
        blocks.get(6).setColor(blocks.get(8).getColor());
        blocks.get(8).setColor(blocks.get(2).getColor());
        blocks.get(2).setColor(c1);

        blocks.get(1).setColor(blocks.get(3).getColor());
        blocks.get(3).setColor(blocks.get(7).getColor());
        blocks.get(7).setColor(blocks.get(5).getColor());
        blocks.get(5).setColor(c2);
    }

    /**
     * A method to check if the plate has symmetry or not
     * @return true, if it's symmetrical,false if it's not
     */
    public boolean symmetryCheck(){
        boolean f2 = (blocks.get(1).getColor() ==  blocks.get(3).getColor() && blocks.get(3).getColor() == blocks.get(5).getColor() && blocks.get(5).getColor() == blocks.get(7).getColor());
        boolean f1 =(blocks.get(0).getColor() == blocks.get(2).getColor() && (blocks.get(2).getColor() == blocks.get(8).getColor()) && blocks.get(8).getColor() == blocks.get(6).getColor());
        return f1 && f2;
    }

    public ArrayList<Block> getBlocks(){
        return blocks;
    }

    /**
     * A method that checks if the three blocks in a direction has a same color or not
     * 'c' is for "cross" and 'o' is for "orib"
     * @param direction the direction we want it to check the color
     * @param num the colom to check
     * @return if they have the same color the color,if not 'n'(no color)
     */
    public char blocksSameColor(char direction,int num){
        switch(direction){
            case('v') : {
                for(int i = 1; i < 4; i++)
                    if(blocks.get(3 * (num - 1) + i - 1).getColor() != blocks.get(3 * (num - 1)).getColor())
                        return 'n';
                    return blocks.get(3 * (num - 1)).getColor();
            }
            case('h') : {
                for(int i = num; i < num + 7; i = i+ 3)
                    if(blocks.get(i- 1).getColor() != blocks.get(num- 1).getColor())
                        return 'n';
                    return blocks.get(num- 1).getColor();
            }
            case('c') : {
                for(int i = 0; i < 9; i += 4)
                    if(blocks.get(i).getColor() != blocks.get(0).getColor())
                        return 'n';
                    return blocks.get(0).getColor();
            }
            case('o') : {
                for(int i = 2; i < 7; i += 2)
                    if(blocks.get(i).getColor() !=  blocks.get(2).getColor())
                        return 'n';
                    return blocks.get(2).getColor();
            }
            default:
                System.out.println("Error in blockSAmeCheck");
                return 'n';
        }
    }
}
