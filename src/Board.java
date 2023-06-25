import java.util.ArrayList;
/**
 * A class to define a board with 4 plate,
 */
public class Board {
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_BLUE = "\033[1;94m";
    public static final String ANSI_WHITE = "\033[0;30m";
    private ArrayList<Plate> plates;

    /**
     * A constructor to make a board with 4 plate
     */
    public Board(){
        plates = new ArrayList<>();
        for(int i = 1; i < 5; i++){
            plates.add(new Plate(i));
        }
    }

    /**
     * A method to print the board
     */
    public void printBoard() {
        int plateNum , blockNum;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 7; k++) {
                    if (k == 3) {
                        System.out.print(" | ");
                        continue;
                    }
                    plateNum = plateNumTeller(i,k);
                    blockNum = blockNumTeller(k);
                    if(plates.get(plateNum).getBlocks().get((3 * j ) + blockNum ).getColor() == 'n')
                        System.out.print(" " + ANSI_WHITE + "\u25CF" + ANSI_RESET + " ");
                    else
                        printBlock(plates.get(plateNum).getBlocks().get((3 * j) + blockNum).getColor());

                }
                System.out.println();
            }
            System.out.println("- - - - - - - - - - -");
        }
    }

    /**
     * A method to tell us witch plate num we are int printBoard
     * @param i i in printBoard
     * @param k k in printBoard
     * @return the number of plate
     */
    private int plateNumTeller(int i, int k){
        switch (i){
            case (0) : {
                if(k < 3)
                    return 0;
                else
                    return 1;
            }
            case (1) : {
                if(k < 3)
                    return 2;
                else
                    return 3;
            }
            default:
                System.out.println("Just...How?...");
                return 0;
            }
    }

    /**
     * A method to tell the number of block in printBoard
     * @param k k in printBoard
     * @return the number of block
     */
    private int blockNumTeller(int k){
        if(k < 3)
            return k;
        else
            return ((k - 1) % 3);
    }

    /**
     * A method to print colored blocks
     * @param color the color of the block
     */
    private void printBlock(char color){
        switch (color){
            case('r') : {
                System.out.print(" " + ANSI_RED + "\u25CF" + ANSI_RESET + " ");
                break;
            }
            case('b') : {
                System.out.print(" " + ANSI_BLUE + "\u25CF" + ANSI_RESET + " ");
            }
        }
    }

    /**
     * A method to rotate the chosen plate
     * @param platNum the plate to rotate
     */
    public void plateRotater(int platNum){
        if(platNum != 0)
            plates.get(platNum - 1).rotate();
    }
    /**
     * A method to checks if any players wins
     * @return true if someone wins,false if not
     */
    public boolean checkWin(){
        //Checks  horizontal
            for(int i = 0; i < 2; i++)
                for(int j = 1; j < 4; j++)
                    if(i == 0)
                        if(plates.get(0).blocksSameColor('h',j) == plates.get(1).blocksSameColor('h',j) && plates.get(0).blocksSameColor('h',j) != 'n'){
                            System.out.println(plates.get(0).blocksSameColor('h',j) + " is Winner!");
                            return true;
                        }
                        else
                        if(plates.get(2).blocksSameColor('h',j) == plates.get(3).blocksSameColor('h',j) && plates.get(3).blocksSameColor('h',j) != 'n'){
                            System.out.println(plates.get(0).blocksSameColor('h',j) + " is Winner!");
                            return true;
                        }
        for(int i = 0; i < 2; i++)
            for(int j = 1; j < 4; j++)
                if(i == 0)
                    if(plates.get(0).blocksSameColor('v',j) == plates.get(2).blocksSameColor('v',j) && plates.get(0).blocksSameColor('v',j) != 'n'){
                        System.out.println(plates.get(0).blocksSameColor('v',j) + " is Winner!");
                        return true;
                    }
                    else
                    if(plates.get(1).blocksSameColor('h',j) == plates.get(3).blocksSameColor('h',j) && plates.get(3).blocksSameColor('v',j) != 'n'){
                        System.out.println(plates.get(0).blocksSameColor('v',j) + " is Winner!");
                        return true;
                    }

        //check cross
        if((plates.get(3).blocksSameColor('c',0) == plates.get(0).blocksSameColor('c',0)) && plates.get(0).blocksSameColor('c',0) != 'n'){
            System.out.println(plates.get(0).blocksSameColor('c',0) + " is Winner!");
            return true;
        }
        //check other cross (orib)
        if((plates.get(1).blocksSameColor('o',0) == plates.get(2).blocksSameColor('o',0) && plates.get(2).blocksSameColor('o',0)  != 'n')){
            System.out.println(plates.get(0).blocksSameColor('o',0) + " is Winner!");
            return true;
        }
        for (Plate p: plates) {
            for (Block b: p.getBlocks()) {
                if(b.getColor() == 'n')
                    return false;
            }
        }
        System.out.println("----Tie----");
        return true;


        }

    /**
     * A method that puts a disk on board by changing the color of block
     * @param plateNum the number of plate
     * @param blockNum the number of block in plate
     * @param color the color of the player
     */
        public boolean putDisk(int plateNum,int blockNum,char color){
            if(plates.get(plateNum - 1).getBlocks().get(blockNum - 1).getColor() != 'n')
                return false;
        plates.get(plateNum - 1).getBlocks().get(blockNum - 1).setColor(color);
        return true;
        }
        public boolean centralSymmetryCheck(){
            for (Plate p:plates
                ) {
                if(p.symmetryCheck() == true)
                    return true;
            }
            return false;
        }
    }


